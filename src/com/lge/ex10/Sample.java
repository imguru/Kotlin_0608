package com.lge.ex10;

// Version 3 - IODH
//  => Initialization on Demand Holder Pattern
class Cursor {
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


// Version 2 - Lazy Initialization
//   문제점: getInstance가 스레드 안전하지 않다.
/*
class Cursor {
    private static Cursor sInstance = null;

    private Cursor() {
    }

    // 문제점: 한번의 동기화 이슈 때문에, 이후의 성능에 악영향을 미친다.
    // => DCLP(Double Check Locking Pattern)
    // => 코드가 선언적이지 않다.
    public static Cursor getInstance() {
        if (sInstance == null) {
            synchronized (Cursor.class) {
                if (sInstance == null)
                    sInstance = new Cursor();
            }
        }
        return sInstance;
    }

    /*
    public static Cursor getInstance() {
        if (sInstance == null)
            sInstance = new Cursor();
        return sInstance;
    }

}
*/


// Version 1
//  문제점: 프로그램 내부에서 싱글톤이 많을 경우, 프로그램의 로드 속도에 문제가 생긴다.
//  해결방법: getInstance()가 처음 호출되는 시점에 객체를 생성하도록 변경한다.
//        => 지연 초기화
/*
class Cursor {
    private static final Cursor INSTANCE = new Cursor();

    // 외부에서 생성할 수 없다.
    private Cursor() {
    }

    public static Cursor getInstance() {
        return INSTANCE;
    }
}
*/


public class Sample {
    public static void main(String[] args) {

    }
}
