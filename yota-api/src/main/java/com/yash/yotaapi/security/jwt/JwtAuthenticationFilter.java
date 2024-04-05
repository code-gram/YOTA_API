package com.yash.yotaapi.security.jwt;

import com.yash.yotaapi.exception.ApplicationException;
import com.yash.yotaapi.security.userdetails.CustomUserDetails;
import com.yash.yotaapi.security.userdetails.CustomUserDetailsServiceImpl;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Project Name - YOTA_API
 * <p>
 * IDE Used - IntelliJ IDEA
 *
 * @author - yashr
 * @since - 05-04-2024
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    private static final String TOKEN_PREFIX = "Bearer ";

    @Autowired
    private CustomUserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String header = request.getHeader("Authorization");
        String userName = null;
        String token = null;

        if (StringUtils.isNotEmpty(header)) {
            if (StringUtils.startsWith(header, TOKEN_PREFIX)) {
                token = StringUtils.substring(header, 7);
                try {
                    userName = this.jwtTokenHelper.getUserNameFromToken(token);
                } catch (IllegalArgumentException e) {
                    LOGGER.error("Unable to get Jwt Token !!",
                            new ApplicationException("Unable to get Jwt Token !!"));
                } catch (ExpiredJwtException e) {
                    LOGGER.error("Jwt Token has expired !!",
                            new ApplicationException("Jwt Token has expired !!"));
                } catch (MalformedJwtException e) {
                    LOGGER.error("Invalid Jwt !!",
                            new ApplicationException("Invalid Jwt !!"));
                }
            } else
                LOGGER.info("Jwt Token does not begin with Bearer.");
        }

        if (StringUtils.isNotEmpty(userName)
                && SecurityContextHolder.getContext().getAuthentication() == null) {
            CustomUserDetails userDetails = null;
            userDetails = this.userDetailsService.loadUserByUsername(userName);

            if (ObjectUtils.isNotEmpty(userDetails)) {
                if (this.jwtTokenHelper.validateToken(token, userDetails)) {

                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,
                            null,
                            userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                } else
                    LOGGER.error("Invalid Jwt token !!",
                            new ApplicationException("Invalid Jwt token !!"));
            } else
                LOGGER.error("Wrong User details provided !!",
                        new ApplicationException("Wrong User details provided !!"));
        }
        filterChain.doFilter(request, response);
    }
}
