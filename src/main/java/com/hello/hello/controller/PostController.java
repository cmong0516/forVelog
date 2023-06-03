package com.hello.hello.controller;

import com.hello.hello.domain.dto.request.NewPostRequest;
import com.hello.hello.domain.dto.response.PostResponse;
import com.hello.hello.service.PostService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/post")
    public Long newPost(@RequestBody @Valid NewPostRequest newPostRequest, HttpServletRequest httpServletRequest) {
        return postService.save(newPostRequest, httpServletRequest);
    }

    @GetMapping("/post")
    public List<PostResponse> allPost() {
        return postService.findAll();
    }
}
