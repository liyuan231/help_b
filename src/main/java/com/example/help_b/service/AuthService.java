package com.example.help_b.service;

import com.example.help_b.model.SysUser;

public interface AuthService {
    SysUser register(SysUser sysUser) throws Exception;

    String refreshToken(String token);
}
