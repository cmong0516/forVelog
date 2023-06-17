package com.hello.hello.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OAuth2Controller {
    @GetMapping("/login/oauth2/code/google")
    public void googleLogin(HttpServletRequest httpServletRequest) {
        OAuth2AuthenticationToken authentication = (OAuth2AuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        OAuth2User oAuth2User = authentication.getPrincipal();

        System.out.println("oAuth2User.toString() = " + oAuth2User.toString());

    }

    @GetMapping("/")
    public String index() {
        return "index.html";
    }
}
