package com.hello.hello.repository;

import com.hello.hello.domain.entity.Member;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CustomMemberRepositoryImpl implements CustomMemberRepository{
    private final JPAQueryFactory jpaQueryFactory;

    public Long save() {
        Member member = Member.builder().email("광민@광민.com").name("5광민").password("555555").build();
//        QMember qMember = QMember.member;
//        jpaQueryFactory.insert(qMember)
//                .set(qMember.email, member.getEmail())
//                .set(qMember.name, member.getName())
//                .set(qMember.password, member.getPassword())
//                .execute();

        return member.getId();
    }
}
