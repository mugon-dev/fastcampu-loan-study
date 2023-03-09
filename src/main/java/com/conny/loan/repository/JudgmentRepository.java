package com.conny.loan.repository;

import com.conny.loan.domain.Judgment;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JudgmentRepository extends JpaRepository<Judgment, Long> {

    Optional<Judgment> findByApplicationId(Long applicationId);
}