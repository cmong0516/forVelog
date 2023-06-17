package com.hello.hello.controller;

import com.hello.hello.domain.dto.request.LoginMemberRequest;
import com.hello.hello.domain.dto.request.SignUpDto;
import com.hello.hello.domain.dto.request.UpdateMemberRequest;
import com.hello.hello.domain.dto.response.LoginMemberResponse;
import com.hello.hello.repository.MemberJpaRepository;
import com.hello.hello.service.MemberService;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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

    @PostMapping("/members")
    public ResponseEntity<Long> createMember(@RequestBody @Valid SignUpDto signUpDto) {
        if (memberJpaRepository.findByEmail(signUpDto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 회원 입니다.");
        }

        Long memberId = memberService.saveMember(signUpDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(memberId);
    }

    @PostMapping("/login")
    public LoginMemberResponse login(@RequestBody(required = false) @Valid LoginMemberRequest loginMemberRequest,
                                     HttpServletRequest httpServletRequest) {
        return memberService.login(loginMemberRequest, httpServletRequest);
    }

    @PutMapping("/members")
    public LoginMemberResponse updateMember(@RequestBody(required = false) UpdateMemberRequest updateMemberRequest,
                                            HttpServletRequest httpServletRequest) {

        return memberService.updateMember(updateMemberRequest, httpServletRequest);
    }

    @DeleteMapping("/members/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);

        return ResponseEntity.noContent().build();
    }

}