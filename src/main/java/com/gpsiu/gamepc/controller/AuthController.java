package com.gpsiu.gamepc.controller;

import com.gpsiu.gamepc.domain.Member;
import com.gpsiu.gamepc.dto.Login;
import com.gpsiu.gamepc.service.AuthService;
import com.gpsiu.gamepc.token.Token;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/auth")
@RequiredArgsConstructor
@RestController
public class AuthController {
    private final AuthService authService;

    @PreAuthorize("permitAll()")
    @PostMapping("/login")
    public ResponseEntity<Token> authenticate(@RequestBody Login login){
        return authService.authenticate(login.getUsername(), login.getPassword())
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PreAuthorize("permitAll()")
    @PostMapping("/reissue")
    public ResponseEntity<Token> reissue(@RequestBody Token token){
        return authService.reissue(token.getRefreshToken())
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}