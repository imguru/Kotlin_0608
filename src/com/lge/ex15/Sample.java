package com.lge.ex15;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

// Java's Lambda - Java 8

@FunctionalInterface
interface Clickable {
    void foo();
}


public class Sample {
    static List<Integer> filter(List<Integer> data, Predicate<Integer> predicate) {
        List<Integer> result = new ArrayList<>();
        for (Integer e : data) {
            if (predicate.test(e))
                result.add(e);
        }
        return result;
    }
    // Java 에서는 interface의 추상 메소드가 한개인 인터페이스를 Functional Interface라고 합니다.

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

        filter(list, new Predicate<Integer>() {
            @Override
            public boolean test(Integer e) {
                return e % 2 == 0;
            }
        });
        // Java 8에서는 Functional Interface에 대해서 람다표현식을 지원한다.
        filter(list, (Integer e) -> {
            return e % 2 == 0;
        });

        // 타입 추론이 가능하다. - 인자 타입 추론이 가능하다.
        filter(list, (e) -> {
            return e % 2 == 0;
        });

        // return 생략 가능하다. - 1줄
        filter(list, e -> e % 2 == 0);
    }
    // 익명의 객체는 .class를 생성합니다.
    // => 프로그램 내부에서 익명의 객체를 많이 사용할 경우, class 로드에 대한 부담이 있다.

    // 람다 표현식은 익명 객체와 다르게 동작합니다.
    // => class 만들어지지 않고, 처음 람다의 코드가 실행되는 시점에 바이트 코드가 생성됩니다.
    //    그것을 계속 실행합니다.

    // Kotlin -> Java 6
    // Kotlin -> Java 8 (O)

    /*
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(10);

        // Immutable List를 만드는 방법 - Decorator Pattern
        //  - 실행 시간에 기능을 변경(확장)한다.
        List<Integer> list2 = Collections.unmodifiableList(list);
        list2.add(20);
    }
    */
}
