package com.example.racecondition.service;

public interface StockService {

    /**
     * Stock 의 수량을 증가.
     * @param id Stock ID
     * @param quantity 증가시킬 수량
     */
    void increase(Long id, Long quantity);

}
