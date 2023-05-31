package com.hello.hello.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpDto {
    private String email;
    private String name;
    private String password;
    private String teamName;

    @Builder
    public SignUpDto(String email, String name, String password,String teamName) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.teamName = teamName;
    }
}
