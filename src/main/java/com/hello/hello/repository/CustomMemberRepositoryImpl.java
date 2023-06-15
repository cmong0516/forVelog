package com.hello.hello.repository;

import com.hello.hello.domain.entity.Member;
import com.hello.hello.domain.entity.QMember;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CustomMemberRepositoryImpl implements CustomMemberRepository{
    private final JPAQueryFactory jpaQueryFactory;

}
