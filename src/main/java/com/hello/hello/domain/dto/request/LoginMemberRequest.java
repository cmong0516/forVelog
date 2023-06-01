package com.hello.hello.domain.dto.request;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginMemberRequest {
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
