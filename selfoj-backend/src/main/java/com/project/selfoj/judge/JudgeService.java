package com.project.selfoj.judge;

import com.project.selfoj.model.vo.QuestionSubmitVo;
import org.springframework.stereotype.Service;

/**
 * @Author: Laidor
 * @Description: 判题服务
 * @Date:2024/11/27 下午 11:01
 */
@Service
public interface JudgeService {

    QuestionSubmitVo doJudge(long questionSubmitId);
}
