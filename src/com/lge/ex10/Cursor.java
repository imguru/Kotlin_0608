package com.lge.ex10;

public class Cursor {
    // 중첩 클래스의 정적 필드는 처음 접근되는 시점에 생성된다.
    // static final은 생성 시점에 대한 스레드 안전성을 언어가 보장한다.
    private static class Singleton {
        private static final Cursor INSTANCE = new Cursor();
    }

    private Cursor() {
    }

    public static Cursor getInstance() {
        return Singleton.INSTANCE;
    }
}
