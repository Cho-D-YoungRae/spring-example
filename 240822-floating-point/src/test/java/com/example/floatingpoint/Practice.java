package com.example.floatingpoint;

import org.junit.jupiter.api.Test;

class Practice {

    @Test
    void maxValue() {
        System.out.println(String.format("%.0f", Double.MAX_VALUE));
        System.out.println(Long.MAX_VALUE);
    }

    @Test
    void practice1() {
        System.out.println(Integer.toBinaryString(Float.floatToIntBits(-118.625F)));
    }
}
