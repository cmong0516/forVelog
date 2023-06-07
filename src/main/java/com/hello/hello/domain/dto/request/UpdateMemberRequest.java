package com.hello.hello.domain.dto.request;

import lombok.Getter;

@Getter
public class UpdateMemberRequest {

    private String email;
    private String password;
    private String name;
}
