package com.example.help_b.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class GitHubUser extends BasicUser{
    public void setName(String name){
        super.setUsername(name);
    }
}
