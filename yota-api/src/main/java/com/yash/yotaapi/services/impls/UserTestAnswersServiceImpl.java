package com.yash.yotaapi.services.impls;

import com.yash.yotaapi.entity.UserTestAnswer;
import com.yash.yotaapi.repositories.UserTestAnswersRepository;
import com.yash.yotaapi.services.IServices.UserTestAnswersService;
import org.springframework.beans.factory.annotation.Autowired;

public class UserTestAnswersServiceImpl implements UserTestAnswersService {

    private final UserTestAnswersRepository userTestAnswersRepository;

    @Autowired
    public UserTestAnswersServiceImpl(UserTestAnswersRepository userTestAnswersRepository) {
        this.userTestAnswersRepository = userTestAnswersRepository;
    }

    @Override
    public UserTestAnswer saveUserTestAnswers(UserTestAnswer userTestAnswers) {
        return userTestAnswersRepository.save(userTestAnswers);
    }
}
