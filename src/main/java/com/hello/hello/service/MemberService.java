package com.hello.hello.service;

import com.hello.hello.domain.Authority;
import com.hello.hello.domain.dto.request.LoginMemberRequest;
import com.hello.hello.domain.dto.request.SignUpDto;
import com.hello.hello.domain.dto.response.LoginMemberResponse;
import com.hello.hello.domain.entity.Member;
import com.hello.hello.repository.MemberJpaRepository;
import com.hello.hello.utils.JwtProvider;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {
    private final MemberJpaRepository memberJpaRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;


    public Long saveMember(SignUpDto signUpDto) {
        Member member = Member.builder().email(signUpDto.getEmail()).name(signUpDto.getName())
                .password(passwordEncoder.encode(signUpDto.getPassword())).build();

        Set<Authority> roles = new HashSet<>();

        roles.add(Authority.ROLE_GUEST);

        member.addRole(roles);

        memberJpaRepository.save(member);

        return member.getId();
    }

    public Optional<Member> findMember(Long id) {

        return memberJpaRepository.findById(id);
    }


    @Transactional
    public LoginMemberResponse updateMember(HttpServletRequest httpServletRequest) {
        String resolveToken = jwtProvider.resolveToken(httpServletRequest);
        String memberEmail = jwtProvider.getMember(resolveToken);

        Member member = memberJpaRepository.findByEmail(memberEmail).orElseThrow(RuntimeException::new);

        Set<Authority> roles = new HashSet<>();

        roles.add(Authority.ROLE_GUEST);
        roles.add(Authority.ROLE_USER);

        member.addRole(roles);

        return LoginMemberResponse.builder().email(member.getEmail()).name(member.getName()).roles(member.getRoles()).build();
    }

    public void deleteMember(Long id) {
        memberJpaRepository.deleteById(id);
    }

    public List<Member> findAllMember() {
        return memberJpaRepository.findAll();
    }

    public LoginMemberResponse login(LoginMemberRequest loginMemberRequest) {
        Member member = memberJpaRepository.findByEmail(loginMemberRequest.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("로그인 정보가 일치하지 않습니다."));

        if (!passwordEncoder.matches(loginMemberRequest.getPassword(),member.getPassword())) {
            throw new IllegalArgumentException("로그인 정보가 일치하지 않습니다.");
        }

        String token = jwtProvider.createToken(member.getEmail(), member.getRoles());

        return LoginMemberResponse.builder().email(member.getEmail()).name(member.getName()).token(token).roles(member.getRoles()).build();
    }
}
