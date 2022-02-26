package com.gpsiu.gamepc.token;

import com.gpsiu.gamepc.domain.Member;
import com.gpsiu.gamepc.repository.MemberRepository;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class TokenProvider implements InitializingBean {
    private final String secretKey;
    private final MemberRepository memberRepository;
    private final Long accessTokenValidityInMilliseconds;
    private final Long refreshTokenValidityInMilliseconds;
    private Key key;

    private final static SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;
    private final static String USER_NOT_FOUND_MSG = "Username[%s] is not found";

    public TokenProvider(
            @Value("${jwt.secret-key}") String secretKey,
            @Value("${jwt.access-token-validity-in-seconds}") Long accessTokenValidityInMilliseconds,
            @Value("${jwt.refresh-token-validity-in-seconds}") Long refreshTokenValidityInMilliseconds,
            MemberRepository memberRepository) {
        this.secretKey = secretKey;
        this.accessTokenValidityInMilliseconds = accessTokenValidityInMilliseconds;
        this.refreshTokenValidityInMilliseconds = refreshTokenValidityInMilliseconds;
        this.memberRepository = memberRepository;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        key = Keys.hmacShaKeyFor(keyBytes);
    }

    public Token createToken(String username){
        Long now = new Date().getTime();
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format(USER_NOT_FOUND_MSG, username))
                );

        String accessToken = Jwts.builder()
                .claim("username", member.getUsername())
                .claim("name", member.getName())
                .claim("role", member.getRole())
                .setExpiration(new Date(now + accessTokenValidityInMilliseconds))
                .signWith(key, SIGNATURE_ALGORITHM)
                .compact();
        String refreshToken = Jwts.builder()
                .claim("username", member.getUsername())
                .setExpiration(new Date(now + refreshTokenValidityInMilliseconds))
                .signWith(key, SIGNATURE_ALGORITHM)
                .compact();
        return new Token(accessToken, refreshToken);
    }

    public boolean validateToken(String token) {
        try {
            Jwts
                    .parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            // 잘못된 JWT 서명입니다.
        } catch (ExpiredJwtException e) {
            // 만료된 JWT 토큰입니다.
        } catch (UnsupportedJwtException e) {
            // 지원되지 않는 JWT 토큰입니다.
        } catch (IllegalArgumentException e) {
            // JWT 토큰이 잘못되었습니다.
        }
        return false;
    }

    public Authentication getAuthentication(String token) {
        Claims claims = getClaims(token);
        Collection<? extends GrantedAuthority> authority =
                Arrays.stream(claims.get("role").toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());
        return new UsernamePasswordAuthenticationToken(claims.get("username"), null, authority);
    }

    private Claims getClaims(String token) {
        try {
            return Jwts
                    .parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }
}
