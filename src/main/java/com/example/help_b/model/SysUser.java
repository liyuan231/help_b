package com.example.help_b.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysUser extends BasicUser{
    SimpleDateFormat simpleDateFormat;
    private String password;
    private String lastPasswordRestDate;
    public SysUser(String username,String password,String created_at){
        this.setUsername(username);
        this.password=password;
        this.setCreated_at( created_at);
        this.setUpdated_at(this.getCreated_at());
        this.setRole(new Role((long) 1));
    }
}
