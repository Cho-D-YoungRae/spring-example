package com.example.racecondition.facade;

import com.example.racecondition.repository.NamedLockRepository;
import com.example.racecondition.service.NamedLockStockService;
import com.example.racecondition.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NamedLockStockFacade implements StockService {

    private final NamedLockStockService stockService;

    private final NamedLockRepository lockRepository;

    @Override
    public void increase(Long id, Long quantity) {
        try {
            lockRepository.getLock("stock:" + id.toString());
            stockService.increase(id, quantity);
        } finally {
            lockRepository.releaseLock("stock:" + id.toString());
        }
    }
}
