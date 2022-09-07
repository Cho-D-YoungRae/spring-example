package com.example.nonfk.repository;

import com.example.nonfk.domain.FkPost;
import com.example.nonfk.domain.Member;
import com.example.nonfk.domain.NonFkPost;

import java.util.Optional;

public interface MemberRepositoryCustom {

    Optional<Member> findByFkPost(FkPost fkPost);

    Optional<Member> findByNonFkPost(NonFkPost nonFkPost);
}
