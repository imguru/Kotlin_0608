package com.lge.ex15;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sample {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(10);

        // Immutable List를 만드는 방법 - Decorator Pattern
        //  - 실행 시간에 기능을 변경(확장)한다.
        List<Integer> list2 = Collections.unmodifiableList(list);
        list2.add(20);


    }
}
