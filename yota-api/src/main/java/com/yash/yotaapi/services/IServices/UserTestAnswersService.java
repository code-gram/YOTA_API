package com.yash.yotaapi.services.IServices;

import com.yash.yotaapi.dto.UserTestAnswerDto;
import com.yash.yotaapi.entity.UserTestAnswer;

public interface UserTestAnswersService {

    UserTestAnswer saveUserTestAnswers(UserTestAnswerDto userTestAnswerDto);
}
