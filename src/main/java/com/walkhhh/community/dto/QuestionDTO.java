package com.walkhhh.community.dto;

import lombok.Data;

/**
 * @author HUANG BAIRUI
 * @create 2022-02-10 22:44
 */
@Data
public class QuestionDTO {
    private Integer id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;
    private User user;
}
