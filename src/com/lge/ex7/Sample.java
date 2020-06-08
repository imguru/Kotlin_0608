package com.lge.ex7;


import ex7.Person;

// 생성자의 인자가 많을 경우, 빌더 패턴을 고려해라.
class User {
    private String name;
    private int age;
    private String address;
    private String school;

    static class Builder {
        private String name;
        private int age;
        private String address;
        private String school;

        public Builder(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder setSchool(String school) {
            this.school = school;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

    private User(Builder b) {
        this.name = b.name;
        this.age = b.age;
        this.address = b.address;
        this.school = b.school;
    }
}

public class Sample {
    public static void main(String[] args) {
        Person person = new Person("Tom", 42);

        String a = "Tom";
        String b = "Suwon";
        String c = "Seoul";
        int d = 42;

        User user = new User.Builder(a, d)
                .setAddress(c)
                .setSchool(b)
                .build();

//        // 각각의 값이 어떤 인자로 전달되는지 쉽게 알 수 없다.
//        User user2 = new User(a, d, b, c);
    }
}


/*
class User {
    private String name;
    private int age;
    private String address;
    private String school;

    // Telescopiong Constructor Pattern - Java's Idiom
    public User(String name, int age, String address, String school) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.school = school;
    }

    public User(String name, int age, String address) {
        this(name, age, address, "");
    }

    public User(String name, int age) {
        this(name, age, "", "");
    }
}

public class Sample {
    public static void main(String[] args) {
        User user1 = new User("Tom", 42);

        String a = "Tom";
        String b = "Suwon";
        String c = "Seoul";
        int d = 42;
        // 각각의 값이 어떤 인자로 전달되는지 쉽게 알 수 없다.
        User user2 = new User(a, d, b, c);
    }
}
*/