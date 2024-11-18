package com.project.selfoj.model.dto.question;

import lombok.Data;

/**
 * @Author: Laidor
 * @Description: 题目用例
 * 加业务前缀的好处，防止多个表都有类似的类，产生冲突;不加的前提，因为可能这个类是多个业务之间共享的
 * @Date:2024/11/16 上午 12:09
 */
@Data
public class JudgeCase {
    /**
     * 输入用例
     */
    private String input;

    /**
     * 输出用例
     */
    private String output;

}
