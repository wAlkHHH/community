package com.walkhhh.community.dto;

import lombok.Data;

/**
 * @author HUANG BAIRUI
 * @create 2022-02-10 9:50
 */
@Data
public class Question {
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
}
