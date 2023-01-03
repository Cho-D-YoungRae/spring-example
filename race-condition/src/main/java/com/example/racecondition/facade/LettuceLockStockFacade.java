package com.example.racecondition.facade;

import com.example.racecondition.repository.RedisLockRepository;
import com.example.racecondition.service.StockService;
import com.example.racecondition.service.StockServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LettuceLockStockFacade implements StockService {

    private final RedisLockRepository redisLockRepository;

    private final StockServiceImpl stockService;

    @Override
    public void increase(Long key, Long quantity) {
        while (!redisLockRepository.lock(key)) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            stockService.increase(key, quantity);
        } finally {
            redisLockRepository.unlock(key);
        }
    }
}
