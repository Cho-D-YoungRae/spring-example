package com.example.racecondition.service;

import com.example.racecondition.domain.Stock;
import com.example.racecondition.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;

    @Override
    @Transactional
    public void increase(Long id, Long quantity) {
        Stock stock = stockRepository.findById(id).orElseThrow();
        stock.increase(quantity);
    }

}
