package com.example.nonfk.repository;

import com.example.nonfk.domain.FkPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FkPostRepository extends JpaRepository<FkPost, Long> {
}
