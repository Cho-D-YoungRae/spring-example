package com.example.nonfk.domain;

import com.example.nonfk.config.JpaConfig;
import com.example.nonfk.repository.FkPostRepository;
import com.example.nonfk.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
@Transactional
@Import(JpaConfig.class)
class FkPostTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private FkPostRepository postRepository;

    @Autowired
    private EntityManager em;

    @Test
    void createWithMember() {
        Member member = memberRepository.save(Member.builder()
                .name("test-member")
                .build());
        FkPost post = postRepository.save(FkPost.builder()
                .member(member)
                .title("test-title")
                .content("test-content")
                .build());
        em.flush();
        em.clear();

        FkPost foundPost = postRepository.findById(post.getId()).orElseThrow();

        assertThat(foundPost.getTitle()).isEqualTo("test-title");
        assertThat(foundPost.getContent()).isEqualTo("test-content");
        assertThat(foundPost.getMember().getId()).isEqualTo(member.getId());
        assertThat(foundPost.getMember().getName()).isEqualTo("test-member");
    }

    @Test
    void createWithoutMember() {

        assertThatThrownBy(() -> postRepository.save(FkPost.builder()
                .member(Member.builder().id(1L).build())
                .title("test-title")
                .content("test-content")
                .build()))
                .isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    void findMemberByFkPost_Querydsl() {
        Member member = memberRepository.save(Member.builder()
                .name("test-member")
                .build());
        FkPost post = postRepository.save(FkPost.builder()
                .member(member)
                .title("test-title")
                .content("test-content")
                .build());
        em.flush();
        em.clear();

        Member foundMember = memberRepository.findByFkPost(post).orElseThrow();

        assertThat(foundMember.getId()).isEqualTo(member.getId());
        assertThat(foundMember.getName()).isEqualTo("test-member");
    }
}