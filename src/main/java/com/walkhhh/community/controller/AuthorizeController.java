package com.walkhhh.community.controller;

import com.walkhhh.community.dto.AccessTokenDTO;
import com.walkhhh.community.dto.GithubUser;
import com.walkhhh.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author HUANG BAIRUI
 * @create 2022-02-07 8:53
 */
@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setClient_id("b255ff7c80bc017b117f");
        accessTokenDTO.setState(state);
        accessTokenDTO.setClient_secret("f9c4df326a740aed44124e7998f99aca99067764");
        accessTokenDTO.setRedirect_uri("http://localhost:8080/callback");
        String token = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.getUser(token);
        return "index";
    }
}
