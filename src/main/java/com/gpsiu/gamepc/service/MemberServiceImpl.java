package com.gpsiu.gamepc.service;

import com.gpsiu.gamepc.domain.Member;
import com.gpsiu.gamepc.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class MemberServiceImpl implements MemberService, UserDetailsService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    private final static String USER_NOT_FOUND_MSG = "Username[%s] is not found";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return memberRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format(USER_NOT_FOUND_MSG, username))
                );
    }

    @Override
    public Member saveMember(Member member) {
        String plainPassword = member.getPassword();
        String cryptPassword = passwordEncoder.encode(plainPassword);
        member.setPassword(cryptPassword);
        return memberRepository.save(member);
    }

    @Override
    public Optional<Member> findMemberById(Long id) {
        return memberRepository.findById(id);
    }

    @Override
    public Optional<Member> findMemberByUsername(String username) {
        return memberRepository.findByUsername(username);
    }

    @Override
    public Optional<Member> findMemberByName(String name) {
        return memberRepository.findByName(name);
    }

    @Override
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }
}
