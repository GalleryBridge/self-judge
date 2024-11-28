package com.project.selfoj.judge;

import com.project.selfoj.model.entity.QuestionSubmit;


/**
 * @Author: Laidor
 * @Description: 判题服务
 * @Date:2024/11/27 下午 11:01
 */
public interface JudgeService {

    QuestionSubmit doJudge(long questionSubmitId);
}
