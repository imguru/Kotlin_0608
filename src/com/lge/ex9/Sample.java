package com.lge.ex9;

class Hello extends Point {
    public Hello(int x, int y) {
        super(x, y);
    }

    // 객체가 GC에 의해서 수거될 때 호출되는 함수
    //  => finalize를 사용하면 안됩니다.
    //  1) 호출 시점을 알 수 없다.
    //  2) super.finalize(); - 명시적으로 호출하지 않으면, 부모의 finalize는 절대 호출되지 않는다.
    //  3) 호출이 보장되지 않는다.
    @Override
    protected void finalize() throws Throwable {
        // ...
        super.finalize();
    }
}

// 자바에서 객체를 복제하는 방법.
class Point implements Cloneable {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // 복사 생성자
    public Point(Point rhs) {
        this.x = rhs.x;
        this.y = rhs.y;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        // ...
    }

    // 1. Object.clone 재정의가 필요합니다.
    // 2. protected -> public
    // 3. CloneNotSupportedException 예외 처리를 메소드 내부로 변경한다.
    // 4. 공변 반환의 룰 - 반환 타입의 하위 타입으로 변경하는 것을 허용한다.
    // 5. clone()의 구현안에서 Point 객체가 Cloneable 이라는 인터페이스를 구현하고 있는지를 체크한다.
    //   => Cloneable 라는 인터페이스를 구현해야 합니다.
    //   => 타입 체크 목적으로만 사용한다 - mark-up interface
    // : Effective Java 에서는 Clone을 사용하지 말자.
    //  한계) 부모가 Clone을 제공하지 않으면, 자식에서는 Clone을 제공할 수 없다.
    //   => Clone 보다는 복사 생성자를 사용하자.
    //      자신과 동일한 타입을 인자로 받는 생성자
    @Override
    public Point clone() {
        try {
            return (Point) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}

public class Sample {
    public static void main(String[] args) {
        Point p1 = new Point(10, 20);
        Point p2 = p1.clone();

        System.out.println(p1);
        System.out.println(p2);
    }
}
