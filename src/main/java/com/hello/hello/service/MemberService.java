package com.hello.hello.service;

import com.hello.hello.domain.dto.SignUpDto;
import com.hello.hello.domain.entity.Member;
import com.hello.hello.repository.MemberJpaRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberJpaRepository memberJpaRepository;


    public Long saveMember(SignUpDto signUpDto) {
        Member member = Member.builder().email(signUpDto.getEmail()).name(signUpDto.getName())
                .password(signUpDto.getPassword()).build();

        memberJpaRepository.save(member);

        return member.getId();
    }

    public Optional<Member> findMember(Long id) {

        return memberJpaRepository.findById(id);
    }


    @Transactional
    public void updateMember(Long id) {
        Optional<Member> findById = memberJpaRepository.findById(id);
        Member findMember = findById.get();
        findMember.updateMember(findMember.getEmail(),"이창모",findMember.getPassword());
    }

    public void deleteMember(Long id) {
        memberJpaRepository.deleteById(id);
    }

    public List<Member> findAllMember() {
        return memberJpaRepository.findAll();
    }
}
