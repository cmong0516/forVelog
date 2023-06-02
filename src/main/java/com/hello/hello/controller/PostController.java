package com.hello.hello.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {

    @PostMapping("/post")
    public void newPost() {

    }
}
