package com.gpsiu.gamepc.service;

import com.gpsiu.gamepc.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberService {
    Member saveMember(Member member);
    Optional<Member> findMemberById(Long id);
    Optional<Member> findMemberByUsername(String username);
    Optional<Member> findMemberByName(String name);
    List<Member> findMembers();
}
