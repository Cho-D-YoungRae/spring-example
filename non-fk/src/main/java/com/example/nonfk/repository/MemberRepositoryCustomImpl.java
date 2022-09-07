package com.example.nonfk.repository;

import com.example.nonfk.domain.*;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import static com.example.nonfk.domain.QMember.member;

@RequiredArgsConstructor
public class MemberRepositoryCustomImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory query;

    @Override
    public Optional<Member> findByFkPost(FkPost fkPost) {
        QFkPost qFkPost = QFkPost.fkPost;
        return Optional.ofNullable(query
                .select(member)
                .from(qFkPost)
                .join(qFkPost.member, member)
                .where(qFkPost.eq(fkPost))
                .fetchOne()
        );
    }

    @Override
    public Optional<Member> findByNonFkPost(NonFkPost nonFkPost) {
        QNonFkPost qNonFkPost = QNonFkPost.nonFkPost;
        return Optional.ofNullable(query
                .select(member)
                .from(qNonFkPost)
                .join(qNonFkPost.member, member)
                .where(qNonFkPost.eq(nonFkPost))
                .fetchOne()
        );
    }
}
