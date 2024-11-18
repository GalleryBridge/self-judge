package com.project.selfoj.model.dto.question;

import com.project.selfoj.common.PageRequest;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: Laidor
 * @Description: 题目查询请求
 * @Date:2024/11/15 下午 11:58
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class QuestionQueryRequest extends PageRequest implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 标签列表
     */
    private List<String> tags;

    /**
     * 题目答案
     */
    private String answer;

    /**
     * 创建用户 id
     */
    private Long userId;

    private static final long serialVersionUID = 1L;

}
