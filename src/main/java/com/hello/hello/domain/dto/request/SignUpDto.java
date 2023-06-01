package com.hello.hello.domain.dto.request;

import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpDto {
    @NotBlank
    private String email;
    @NotBlank
    private String name;
    @NotBlank
    private String password;

    @Builder
    public SignUpDto(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }
}
