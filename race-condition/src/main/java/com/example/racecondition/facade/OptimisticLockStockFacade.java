package com.example.racecondition.facade;

import com.example.racecondition.service.OptimisticLockStockService;
import com.example.racecondition.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OptimisticLockStockFacade implements StockService {

    private final OptimisticLockStockService optimisticLockStockService;

    @Override
    public void increase(Long id, Long quantity) {
        while (true) {
            try {
                optimisticLockStockService.increase(id, quantity);
                break;
            } catch (OptimisticLockingFailureException e1) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e2) {
                    throw new RuntimeException(e2);
                }
            }
        }
    }
}
