package com.walkhhh.community.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author HUANG BAIRUI
 * @create 2022-02-08 15:09
 */
@Data
public class User {
    private Integer id;
    private String name;
    private String accountId;
    private String token;
    private String avatarUrl;
    private Long gmtCreate;
    private Long gmtModified;

}
