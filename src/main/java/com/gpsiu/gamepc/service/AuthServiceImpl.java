package com.gpsiu.gamepc.service;

import com.gpsiu.gamepc.token.Token;
import com.gpsiu.gamepc.token.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class AuthServiceImpl implements AuthService{
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;

    @Override
    public Optional<Token> authenticate(String username, String password) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username, password);
        try {
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
        } catch (Exception e) {
            return Optional.empty();
        }

        return Optional.ofNullable(tokenProvider.createToken(username));
    }

    @Override
    public Optional<Token> reissue(String refreshToken) {
        if (tokenProvider.validateToken(refreshToken)) {
            String username = tokenProvider.getUsername(refreshToken);
            return Optional.ofNullable(tokenProvider.createToken(username));
        } else {
            return Optional.empty();
        }
    }
}
