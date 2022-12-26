package com.example.jacocosetting;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class JavaFooTest {

    private JavaFoo javaFoo;

    @BeforeEach
    void beforeEach() {
        javaFoo = new JavaFoo();
    }

    @Test
    void partiallyCoveredHelloMethodTest() {
        String actual = javaFoo.hello("펭");
        assertThat(actual).isEqualTo("하");
    }

}