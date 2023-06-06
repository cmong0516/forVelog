package com.hello.hello.service;

import com.hello.hello.domain.Authority;
import com.hello.hello.domain.entity.Member;
import com.hello.hello.domain.entity.Post;
import com.hello.hello.repository.MemberJpaRepository;
import com.hello.hello.repository.PostJpaRepository;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class InitService {

    private final MemberJpaRepository memberJpaRepository;
    private final PostJpaRepository postJpaRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    @Transactional
    public void init() {
        memberInit();
        postInit();
    }

    public void memberInit() {
        for (int i = 0; i < 3; i++) {
            Set<Authority> roles = new HashSet<>();
            roles.add(Authority.ROLE_USER);

            Member member = Member.builder().email("test" + i + "@gmail.com").name("tester" + i).password(
                            passwordEncoder.encode("123123"))
                    .build();

            member.addRole(roles);

            memberJpaRepository.save(member);

        }
    }

    public void postInit() {
        for (int i = 0; i < 3; i++) {
            postJpaRepository.save(Post.builder().title("title"+i).content("content"+i)
                    .member(memberJpaRepository.findByEmail("test"+i+"@gmail.com")
                            .orElseThrow(() -> new UsernameNotFoundException("유저를 찾을수 없습니다.")))
                    .build());
        }
    }
}
