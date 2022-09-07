package com.example.nonfk.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fk_member_id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Builder
    private Member(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
