package com.hello.hello.service;

import com.hello.hello.domain.dto.request.NewPostRequest;
import com.hello.hello.domain.dto.response.PostResponse;
import com.hello.hello.domain.entity.Member;
import com.hello.hello.domain.entity.Post;
import com.hello.hello.repository.MemberJpaRepository;
import com.hello.hello.repository.PostJpaRepository;
import com.hello.hello.utils.JwtProvider;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostJpaRepository postJpaRepository;
    private final MemberJpaRepository memberJpaRepository;
    private final JwtProvider jwtProvider;

    public Long save(NewPostRequest newPostRequest, HttpServletRequest httpServletRequest) {

        String token = httpServletRequest.getHeader("Authorization");
        String getToken = token.replace("Bearer ", "");

        String memberEmail = jwtProvider.getMember(getToken);

        Member member = memberJpaRepository.findByEmail(memberEmail)
                .orElseThrow(() -> new UsernameNotFoundException(memberEmail + " 유저를 찾을수 없습니다."));

        Post post = Post.builder().title(newPostRequest.getTitle()).content(newPostRequest.getContent()).member(member)
                .build();

        postJpaRepository.save(post);

        return post.getId();
    }

    public List<PostResponse> findAll() {

        return postJpaRepository.findAll().stream()
                .map(p -> PostResponse.builder().id(p.getId()).title(p.getTitle()).content(p.getContent()).author(p.getMember().getEmail()).build())
                .collect(
                        Collectors.toList());
    }
}
