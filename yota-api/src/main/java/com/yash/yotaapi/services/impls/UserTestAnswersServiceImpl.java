package com.yash.yotaapi.services.impls;

import com.yash.yotaapi.dto.UserTestAnswerDto;
import com.yash.yotaapi.entity.Questions;
import com.yash.yotaapi.entity.Tests;
import com.yash.yotaapi.entity.UserTestAnswer;
import com.yash.yotaapi.entity.YotaUser;
import com.yash.yotaapi.exceptions.ResourceNotFoundException;
import com.yash.yotaapi.repositories.QuestionsRepository;
import com.yash.yotaapi.repositories.TestRepository;
import com.yash.yotaapi.repositories.UserTestAnswersRepository;
import com.yash.yotaapi.repositories.YotaUserRepository;
import com.yash.yotaapi.services.IServices.UserTestAnswersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserTestAnswersServiceImpl implements UserTestAnswersService {

    private final UserTestAnswersRepository userTestAnswersRepository;

    @Autowired
    public UserTestAnswersServiceImpl(UserTestAnswersRepository userTestAnswersRepository) {
        this.userTestAnswersRepository = userTestAnswersRepository;
    }


    @Autowired
    TestRepository testRepository;

    @Autowired
    QuestionsRepository questionsRepository;

    @Autowired
    YotaUserRepository yotaUserRepository;


    @Override
    public UserTestAnswer saveUserTestAnswers(UserTestAnswerDto userTestAnswerDto) {
        Optional<Tests> test = testRepository.findById(userTestAnswerDto.getTestid());
        if (!test.isPresent()) {
            throw new ResourceNotFoundException("Invalid test ID provided.");
        }

        Optional<Questions> question = questionsRepository.findById(userTestAnswerDto.getQuestionId());
        if (!question.isPresent()) {
            throw new ResourceNotFoundException("Invalid question ID provided.");
        }

        YotaUser yotaUser = yotaUserRepository.getUserByEmail(userTestAnswerDto.getUserEmailId());
        if (yotaUser == null) {
            throw new ResourceNotFoundException("User not found with email: " + userTestAnswerDto.getUserEmailId());
        }

        UserTestAnswer userTestAnswer = new UserTestAnswer();
        userTestAnswer.setUser(yotaUser);
        userTestAnswer.setQuestion(question.get());
        userTestAnswer.setTest(test.get());
        userTestAnswer.setSelectedOption(userTestAnswerDto.getSelectedOption());

        return userTestAnswersRepository.save(userTestAnswer);
    }
}
