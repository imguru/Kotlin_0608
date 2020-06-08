package com.lge.ex1;

import java.util.Collections;

class Point {
    protected int x;
    protected int y;
}

public class Sample {
    static void foo() {}

    public static void main(String[] args) {
        Point point = new Point();
        point.x = 42;    // 같은 패키지에서 protected에 접근 가능하다.
    }
    /*
    public static void main(String[] args) {
        System.out.println("Hello, Java");

        int a = 42;
        long b = a;

        // Immutable Object: 불변 객체
        //  : 같은 값을 갖는 객체는 공유된다.
        String s1 = "hello";
        String s2 = new String("hello");

        if (s1 == s2) {
            System.out.println("Same");
        } else {
            System.out.println("Diff");
        }


        // System.out.println(foo());

        // Collections.sort(list);
        // Arrays.sort(arr);

    }
     */
}
