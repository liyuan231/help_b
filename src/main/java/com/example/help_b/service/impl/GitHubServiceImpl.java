package com.example.help_b.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.help_b.model.AccessToken;
import com.example.help_b.model.GitHubUser;
import com.example.help_b.service.GitHubService;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class GitHubServiceImpl implements GitHubService {
    public String getAccessToken(AccessToken accessToken){
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessToken));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String b= response.body().string();
            return b.split("&")[0].split("=")[1];
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public GitHubUser getGitHubUser(String accessToken){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+accessToken)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String body= response.body().string();
            return JSON.parseObject(body,GitHubUser.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
