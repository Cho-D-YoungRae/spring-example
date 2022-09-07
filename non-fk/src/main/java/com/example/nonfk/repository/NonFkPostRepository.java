package com.example.nonfk.repository;

import com.example.nonfk.domain.Member;
import com.example.nonfk.domain.NonFkPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NonFkPostRepository extends JpaRepository<NonFkPost, Long> {

    Optional<NonFkPost> findByMember(Member member);
}
