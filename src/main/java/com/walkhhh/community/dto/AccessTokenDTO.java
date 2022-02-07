package com.walkhhh.community.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author HUANG BAIRUI
 * @create 2022-02-07 9:01
 */
@Getter
@Setter
public class AccessTokenDTO {
    private String client_id;
//    private String client_address;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;
}
