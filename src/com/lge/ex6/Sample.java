package com.lge.ex6;

interface Clickable {
    void click();

    // Java 8 에서는 인터페이스에 기본 구현을 제공하는 것을 허용합니다.
    //  => default method / defender method
    default void showOff() {
        System.out.println("Clickable!!");
    }
}

class Window implements Clickable {
    @Override
    public void click() {

    }
}


public class Sample {
    public static void main(String[] args) {

    }
}
