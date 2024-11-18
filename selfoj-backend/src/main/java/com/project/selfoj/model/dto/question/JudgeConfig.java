package com.project.selfoj.model.dto.question;

import lombok.Data;

/**
 * @Author: Laidor
 * @Description: 题目配置
 * 加业务前缀的好处，防止多个表都有类似的类，产生冲突;不加的前提，因为可能这个类是多个业务之间共享的
 * @Date:2024/11/16 上午 12:09
 */

@Data
public class JudgeConfig {

    /**
     * 时间限制（ms）
     */
    private Long timeLimit;

    /**
     * 内存限制（KB）
     */
    private Long memoryLimit;

    /**
     * 堆栈限制（KB）
     */
    private Long stackLimit;
}
