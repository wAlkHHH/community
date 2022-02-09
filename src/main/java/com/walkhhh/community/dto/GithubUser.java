package com.walkhhh.community.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author HUANG BAIRUI
 * @create 2022-02-07 10:24
 */
@Data
public class GithubUser {
    private String name;
    private Long id;
    private String bio;
    private String login;
    private Long gmtCreate;
}
