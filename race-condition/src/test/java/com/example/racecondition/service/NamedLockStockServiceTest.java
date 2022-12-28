package com.example.racecondition.service;

import com.example.racecondition.domain.Stock;
import com.example.racecondition.facade.NamedLockStockFacade;
import com.example.racecondition.repository.StockRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class NamedLockStockServiceTest {

    @Autowired
    private NamedLockStockFacade stockService;

    @Autowired
    private StockRepository stockRepository;

    @AfterEach
    void afterEach() {
        stockRepository.deleteAll();
    }

    @Test
    void increase() throws Exception {
        // given
        Stock stock = stockRepository.save(Stock.builder()
                .productId(1234L)
                .quantity(0L)
                .build());

        // when
        long startTime = System.currentTimeMillis();
        int threadNum = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(threadNum);
        CountDownLatch latch = new CountDownLatch(threadNum);

        for (int i = 0; i < threadNum; i++) {
            executorService.submit(() -> {
                try {
                    stockService.increase(stock.getId(), 1L);
                } finally {
                    latch.countDown();
                }
            });
        }
        latch.await();

        long runningTime = System.currentTimeMillis() - startTime;

        // then
        Stock foundStock = stockRepository.findById(stock.getId()).orElseThrow();
        assertThat(foundStock.getQuantity()).isEqualTo(100L);
        System.out.println("Running time: " + (runningTime / 1000.0));
    }

}