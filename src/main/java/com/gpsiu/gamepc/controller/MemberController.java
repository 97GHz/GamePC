package com.gpsiu.gamepc.controller;

import com.gpsiu.gamepc.domain.Member;
import com.gpsiu.gamepc.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/member")
@RequiredArgsConstructor
@RestController
public class MemberController {
    private final MemberService memberService;

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @GetMapping("")
    public ResponseEntity<List<Member>> findMembers(){
        return ResponseEntity.ok().body(memberService.findMembers());
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/{username}")
    public ResponseEntity<Member> findMemberByUsername(@PathVariable String username){
        return memberService.findMemberByUsername(username)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }

    @PreAuthorize("permitAll()")
    @PostMapping("")
    public ResponseEntity<Member> saveMember(@RequestBody Member member){
        return ResponseEntity.ok().body(memberService.saveMember(member));
    }
}