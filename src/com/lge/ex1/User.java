package com.lge.ex1;

import java.util.Objects;

// Project Lombok - @Data
//            : toString / equals / hashCode
public class User {
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // 객체 동등성은 클래스를 설계하는 사람이 제공해야 한다.
    // 기본값: 참조 비교
    @Override
    public boolean equals(Object obj) {
        // 1. 참조 동등성
        if (obj == this)
            return true;

        // 2. Null 비교
        if (obj == null)
            return false;

        // 3. User 타입인지 체크
        if (!(obj instanceof User))
            return false;

        // 4. 동등성 비교
        User user = (User) obj;
        // equals: null 대상으로 호출되지 않도록 주의해야 한다.
        // return name.equals(user.name) && age == user.age;
        return Objects.equals(name, user.name) && age == user.age;
    }

    // equals / hashCode
    // : equals를 제공한다면, 반드시 hashCode도 제공해야 한다.

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    public static void main(String[] args) {
        User user1 = new User("Tom", 42);
        User user2 = new User("Tom", 42);
        // User user2 = user1;

        if (user1 == user2) {
            System.out.println("같은 참조!");
        }

        if (user1.equals(user2)) {
            System.out.println("같은 값!");
        }
    }
}
