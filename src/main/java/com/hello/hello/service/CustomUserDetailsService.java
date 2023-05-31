package com.hello.hello.service;

import com.hello.hello.domain.entity.Member;
import com.hello.hello.repository.MemberJpaRepository;
import com.hello.hello.utils.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberJpaRepository memberJpaRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberJpaRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email + "회원을 찾을수 없습니다."));
        return new CustomUserDetails(member);
    }
}
