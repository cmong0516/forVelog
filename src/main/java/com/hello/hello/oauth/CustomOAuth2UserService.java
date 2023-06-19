package com.hello.hello.oauth;

import com.hello.hello.domain.Authority;
import com.hello.hello.domain.entity.Member;
import com.hello.hello.repository.MemberJpaRepository;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final MemberJpaRepository memberJpaRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2UserService<OAuth2UserRequest,OAuth2User> delegate= new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        Map<String, Object> attributes = oAuth2User.getAttributes();

        String email = (String) attributes.get("email");
        String name = (String) attributes.get("name");

        Optional<Member> byEmail = memberJpaRepository.findByEmail(email);

        if (byEmail.isPresent()) {

            Member member = byEmail.get();

            return new DefaultOAuth2User(
                    Collections.singleton(new SimpleGrantedAuthority(member.getRoles().toString())),
                    attributes,
                    userNameAttributeName
            );
        } else {
            Set<Authority> roles = new HashSet<>();
            roles.add(Authority.ROLE_GUEST);
            roles.add(Authority.ROLE_USER);

            Member member = Member.builder().email(email).name(name).roles(roles).build();

            memberJpaRepository.save(member);
        }

        return oAuth2User;
    }
}
