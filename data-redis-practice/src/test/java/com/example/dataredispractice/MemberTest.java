package com.example.dataredispractice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
@Transactional
class MemberTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    EntityManager em;

    @Test
    void practice() {
        Member member = memberRepository.save(Member.builder()
                .name("name")
                .introduction("introduction")
                .build());

        em.flush();
        em.clear();

        Member foundMember = memberRepository.findById(member.getId()).get();
        System.out.println("foundMember = " + foundMember);
    }
}
