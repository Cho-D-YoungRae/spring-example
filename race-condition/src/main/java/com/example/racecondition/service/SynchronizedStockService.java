package com.example.racecondition.service;

import com.example.racecondition.domain.Stock;
import com.example.racecondition.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SynchronizedStockService implements StockService {

    private final StockRepository stockRepository;

    @Override
//    @Transactional
    public synchronized void increase(Long id, Long quantity) {
        Stock stock = stockRepository.findById(id).orElseThrow();
        stock.increase(quantity);
        stockRepository.saveAndFlush(stock);
    }
}
