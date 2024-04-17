package com.yash.yotaapi.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Project Name - YOTASecurityAPI
 * <p>
 * IDE Used - IntelliJ IDEA
 *
 * @author - yash.raj
 * @since - 02-04-2024
 */
@Configuration
public class CommonConfigs {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
