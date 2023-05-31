package com.hello.hello.repository;

import com.hello.hello.domain.entity.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberJpaRepository extends JpaRepository<Member,Long> {
    Optional<Member> findByEmail(String email);
}
