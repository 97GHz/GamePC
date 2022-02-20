package com.gpsiu.gamepc.controller;

import com.gpsiu.gamepc.domain.Member;
import com.gpsiu.gamepc.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/member")
@RequiredArgsConstructor
@RestController
public class MemberController {
    private final MemberService memberService;

    @GetMapping("")
    public ResponseEntity<List<Member>> findMembers(){
        return ResponseEntity.ok().body(memberService.findMembers());
    }

    @GetMapping("/{username}")
    public ResponseEntity<Member> findMemberByUsername(@PathVariable String username){
        return memberService.findMemberByUsername(username)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }

    @PostMapping("")
    public ResponseEntity<Member> saveMember(@RequestBody Member member){
        System.out.println(member.getRole());
        return ResponseEntity.ok().body(memberService.saveMember(member));
    }
}