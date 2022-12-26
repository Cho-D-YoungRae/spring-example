package com.example.jacocosetting;

public class JavaFoo {

    public String hello(String name) {
        return switch (name) {
            case "펭" -> "하";
            case "hello" -> "world";
            default -> "no one";
        };
    }

    public void callMe() {
        System.out.println("Please, call me");
    }
}
