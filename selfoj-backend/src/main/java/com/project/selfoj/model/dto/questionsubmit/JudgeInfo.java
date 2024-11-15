package com.project.selfoj.model.dto.questionsubmit;

import lombok.Data;

/**
 * @Author: Laidor
 * @Description:
 * @Date:2024/11/16 上午 12:14
 */
@Data
public class JudgeInfo {

    private String message;

    private Long memory;

    private Long time;
}
