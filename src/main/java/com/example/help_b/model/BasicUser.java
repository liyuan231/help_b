package com.example.help_b.model;

import lombok.Data;

@Data
public class BasicUser {
    private Integer id;
    private String username;
    private String avatar_url;
    private String bio;
    private String created_at;
    private String updated_at;
}
