package com.walkhhh.community.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author HUANG BAIRUI
 * @create 2022-02-08 15:09
 */
@Getter
@Setter
public class User {
    private Integer id;
    private String name;
    private String accountId;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;

}
