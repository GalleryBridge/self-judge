package com.project.selfoj.judge.strategy;

import com.project.selfoj.model.dto.questionsubmit.JudgeInfo;

/**
 * @Author: Laidor
 * @Description: 判题策略
 * @Date:2024/11/28 下午 08:39
 */
public interface JudgeStrategy {

    /**
     * 执行判题
     *
     * @param judgeContext
     * @return
     */
    public JudgeInfo doJudge(JudgeContext judgeContext);
}
