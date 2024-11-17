package com.project.selfoj.model.dto.questionsubmit;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Laidor
 * @Description: 题目提交请求
 * @Date:2024/11/17 下午 04:39
 */
@Data
public class QuestionSubmitAddRequest implements Serializable {

    /**
     * 编程语言
     */
    private String language;

    /**
     * 用户代码
     */
    private String code;

    /**
     * 题目 id
     */
    private Long questionId;

    private static final long serialVersionUID = 1L;
}
