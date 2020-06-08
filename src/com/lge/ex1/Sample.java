package com.lge.ex1;

import java.util.Collections;

class Point {
//    public int x;
    public int y;

    Point(int x, int y) {
//        this.x = x;
        this.y = y;
    }

    public int getX() {
        return 0;
//        return x;
    }

    public void setX(int x) {
//        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}

public class Sample {
    static void foo() {
    }

    public static void main(String[] args) {
        Point point = new Point(10, 20);
        // point.x = 42;    // 같은 패키지에서 protected에 접근 가능하다.
        point.setX(10);
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
