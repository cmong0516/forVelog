package com.hello.hello.domain.dto.response;

import com.hello.hello.domain.Authority;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class LoginMemberResponse {
    private String email;
    private String name;
    private Set<Authority> roles;
    private String token;
}
