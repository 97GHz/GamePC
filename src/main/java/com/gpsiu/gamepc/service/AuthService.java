package com.gpsiu.gamepc.service;

import com.gpsiu.gamepc.token.Token;

import java.util.Optional;

public interface AuthService{
    Optional<Token> authenticate(String username, String password);
    Optional<Token> reissue(String refreshToken);
}