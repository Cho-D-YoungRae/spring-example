package com.example.floatingpoint;

import org.junit.jupiter.api.Test;

class LongServiceTest {

    private LongService longService = new LongService();

    @Test
    void 범위_안의_값() {
        long value = longService.getValueInRange();
        System.out.println(value);
        System.out.println(padLeft(Long.toBinaryString(value)));

        System.out.println((double) value);
        System.out.println(padLeft(Long.toBinaryString(Double.doubleToLongBits(value))));
    }

    @Test
    void 범위_밖의_값() {
        long value = longService.getValueOutOfRange();
        System.out.println(value);
        System.out.println(padLeft(Long.toBinaryString(value)));

        System.out.println(String.format("%.0f", (double) value));
        System.out.println(padLeft(Long.toBinaryString(Double.doubleToLongBits(value))));
    }

    private String padLeft(String value) {
        return String.format("%64s", value).replace(' ', '0');
    }

}