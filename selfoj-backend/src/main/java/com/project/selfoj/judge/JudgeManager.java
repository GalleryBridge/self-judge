package com.project.selfoj.judge;

import com.project.selfoj.judge.strategy.DefaultJudgeStrategy;
import com.project.selfoj.judge.strategy.JavaLanguageJudgeStrategy;
import com.project.selfoj.judge.strategy.JudgeContext;
import com.project.selfoj.judge.strategy.JudgeStrategy;
import com.project.selfoj.model.dto.questionsubmit.JudgeInfo;
import com.project.selfoj.model.entity.QuestionSubmit;
import org.springframework.stereotype.Service;

/**
 * @Author: Laidor
 * @Description: 判题管理 (简化调用)
 * @Date:2024/11/28 下午 09:16
 */
@Service
public class JudgeManager {

    /**
     * 执行判题
     * @param judgeContext
     * @return
     */
    JudgeInfo doJudge(JudgeContext judgeContext){
        QuestionSubmit questionSubmit = judgeContext.getQuestionSubmit();
        JudgeStrategy judgeStrategy = new DefaultJudgeStrategy();
        if (questionSubmit.getLanguage().equals("java"))
            judgeStrategy = new JavaLanguageJudgeStrategy();
        return judgeStrategy.doJudge(judgeContext);
    }
}
