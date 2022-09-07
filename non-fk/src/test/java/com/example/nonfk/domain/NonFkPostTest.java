package com.example.nonfk.domain;

import com.example.nonfk.config.JpaConfig;
import com.example.nonfk.repository.MemberRepository;
import com.example.nonfk.repository.NonFkPostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Transactional
@Import(JpaConfig.class)
class NonFkPostTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private NonFkPostRepository postRepository;

    @Autowired
    private EntityManager em;

    @Test
    void createWithMember() {
        Member member = memberRepository.save(Member.builder()
                .name("test-member")
                .build());
        NonFkPost post = postRepository.save(NonFkPost.builder()
                .member(member)
                .title("test-title")
                .content("test-content")
                .build());
        em.flush();
        em.clear();

        NonFkPost foundPost = postRepository.findById(post.getId()).orElseThrow();

        assertThat(foundPost.getTitle()).isEqualTo("test-title");
        assertThat(foundPost.getContent()).isEqualTo("test-content");
        assertThat(foundPost.getMember().getId()).isEqualTo(member.getId());
        assertThat(foundPost.getMember().getName()).isEqualTo("test-member");
    }

    @Test
    void createWithoutMember() {

        NonFkPost post = postRepository.save(NonFkPost.builder()
                .member(Member.builder().id(10L).build())
                .title("test-title")
                .content("test-content")
                .build());
        em.flush();
        em.clear();

        NonFkPost foundPost = postRepository.findById(post.getId()).orElseThrow();

        assertThat(foundPost.getTitle()).isEqualTo("test-title");
        assertThat(foundPost.getContent()).isEqualTo("test-content");
        assertThat(foundPost.getMember().getId()).isEqualTo(10L);
    }

    @Test
    void findByMember() {
        NonFkPost post = postRepository.save(NonFkPost.builder()
                .member(Member.builder().id(10L).build())
                .title("test-title")
                .content("test-content")
                .build());
        em.flush();
        em.clear();

        NonFkPost foundPost = postRepository.findByMember(Member.builder().id(10L).build()).orElseThrow();

        assertThat(foundPost.getTitle()).isEqualTo("test-title");
        assertThat(foundPost.getContent()).isEqualTo("test-content");
        assertThat(foundPost.getMember().getId()).isEqualTo(10L);
    }


    @Test
    void findMemberByNonFkPost_Querydsl() {
        Member member = memberRepository.save(Member.builder()
                .name("test-member")
                .build());
        NonFkPost post = postRepository.save(NonFkPost.builder()
                .member(member)
                .title("test-title")
                .content("test-content")
                .build());
        em.flush();
        em.clear();

        Member foundMember = memberRepository.findByNonFkPost(post).orElseThrow();

        assertThat(foundMember.getId()).isEqualTo(member.getId());
        assertThat(foundMember.getName()).isEqualTo("test-member");
    }
}