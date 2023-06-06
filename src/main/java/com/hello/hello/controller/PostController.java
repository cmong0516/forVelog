package com.hello.hello.controller;

import com.hello.hello.domain.dto.request.NewPostRequest;
import com.hello.hello.domain.dto.response.PostResponse;
import com.hello.hello.service.PostService;
import java.net.URI;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/posts")
    public ResponseEntity<Long> createPost(@RequestBody @Valid NewPostRequest newPostRequest, HttpServletRequest httpServletRequest) {
        Long postId = postService.save(newPostRequest, httpServletRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(postId)
                .toUri();

        return ResponseEntity.created(location).body(postId);
    }

    @GetMapping("/posts")
    public ResponseEntity<List<PostResponse>> getPostsByTitleAndContent(@RequestParam(required = false) String title,@RequestParam(required = false) String content) {
        List<PostResponse> posts = postService.findPostsByTitleAndContent(title, content);

        return ResponseEntity.ok(posts);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<PostResponse> getPostById(@PathVariable Long id) {
        PostResponse post = postService.findOne(id);
        return ResponseEntity.ok(post);
    }

}
