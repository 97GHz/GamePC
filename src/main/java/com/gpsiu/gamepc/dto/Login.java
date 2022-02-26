package com.gpsiu.gamepc.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class Login {
    private String username;
    private String password;

    @Builder
    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
