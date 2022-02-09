package com.walkhhh.community.provider;

import com.alibaba.fastjson.JSON;
import com.walkhhh.community.dto.AccessTokenDTO;
import com.walkhhh.community.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author HUANG BAIRUI
 * @create 2022-02-07 8:56
 */
@Component
public class GithubProvider {

    public String getAccessToken(AccessTokenDTO accessTokenDTO){
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(JSON.toJSONString(accessTokenDTO), mediaType);
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token") //拿code换token
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            System.out.println(string);
            String[] strings = string.split("&");
            String token = strings[0].split("=")[1];//e.g. access_token=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&scope=user&token_type=bearer
            System.out.println(token);
            return token;
        } catch (IOException e) {
            return null;
        }
    }

    public GithubUser getUser(String accessToken){
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.github.com/user") //用授权的token获取用户信息
                .header("Authorization","token " +accessToken)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            System.out.println(string);
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
//            System.out.println(githubUser);
            return githubUser;
        } catch (IOException e) {
            return null;
        }

    }
}
