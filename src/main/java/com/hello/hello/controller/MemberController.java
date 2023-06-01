package com.hello.hello.controller;

import com.hello.hello.domain.dto.request.LoginMemberRequest;
import com.hello.hello.domain.dto.request.SignUpDto;
import com.hello.hello.domain.dto.response.LoginMemberResponse;
import com.hello.hello.repository.MemberJpaRepository;
import com.hello.hello.service.MemberService;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final MemberService memberService;
    private final MemberJpaRepository memberJpaRepository;

    @PostMapping("/member")
    public Long save(@RequestBody @Valid SignUpDto signUpDto) {
        if (memberJpaRepository.findByEmail(signUpDto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 회원 입니다.");
        }

        return memberService.saveMember(signUpDto);
    }

    @PostMapping("/login")
    public LoginMemberResponse login(@RequestBody @Valid LoginMemberRequest loginMemberRequest) {
        return memberService.login(loginMemberRequest);
    }

    @PutMapping("/member")
    public LoginMemberResponse updateMember(HttpServletRequest httpServletRequest) {

        return memberService.updateMember(httpServletRequest);
    }

    @DeleteMapping("/member/{id}")
    public void deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
    }
}