package com.hello.hello.controller;

import com.hello.hello.domain.dto.SignUpDto;
import com.hello.hello.domain.entity.Member;
import com.hello.hello.service.MemberService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/member")
    public Long save(@RequestBody SignUpDto signUpDto) {
        return memberService.saveMember(signUpDto);
    }

    @GetMapping("/member/{id}")
    public Optional<Member> findMember(@PathVariable Long id) {
        return memberService.findMember(id);
    }

    @PutMapping("/member/{id}")
    public void updateMember(@PathVariable Long id) {
        memberService.updateMember(id);
    }

    @DeleteMapping("/member/{id}")
    public void deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
    }
}