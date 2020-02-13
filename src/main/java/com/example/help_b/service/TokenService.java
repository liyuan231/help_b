package com.example.help_b.service;

import com.example.help_b.model.BasicUser;

public interface TokenService {
    String getToken(BasicUser basicUser);
}
