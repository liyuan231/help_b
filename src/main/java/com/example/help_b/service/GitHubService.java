package com.example.help_b.service;

import com.example.help_b.model.AccessToken;
import com.example.help_b.model.GitHubUser;

public interface GitHubService {
    public GitHubUser getGitHubUser(String accessToken);

    public String getAccessToken(AccessToken accessToken);
}
