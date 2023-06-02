package com.hello.hello.domain.entity;

import com.hello.hello.domain.Authority;
import com.hello.hello.domain.BaseTimeEntity;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String name;
    private String password;

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Authority> roles = new HashSet<>();

    @OneToMany(mappedBy = "member",fetch = FetchType.LAZY)
    private List<Post> posts = new ArrayList<>();

    public void updateMember(String email,String name,String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public void addRole(Set<Authority> roles) {
        this.roles = roles;
    }
}
