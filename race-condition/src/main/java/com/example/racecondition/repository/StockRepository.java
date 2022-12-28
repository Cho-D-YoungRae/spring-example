package com.example.racecondition.repository;

import com.example.racecondition.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;
import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Stock> findWithPessimisticLockById(Long id);

    @Lock(LockModeType.OPTIMISTIC)
    Optional<Stock> findWithOptimisticLockById(Long id);

}
