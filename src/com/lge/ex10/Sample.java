package com.lge.ex10;

// Version 3 - IODH
//  => Initialization on Demand Holder Pattern
// com.lge.ex10.Cursor
/*
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
*/

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


/*
import ex10.Cursor;

public class Sample {
    public static void main(String[] args) {
        System.out.println("main");
        Cursor.INSTANCE.move();
    }
}
*/

// 생성자를 통해 객체를 직접 생성하는 것은 좋지 않다. => '정적 팩토리 메소드'
// 1) 생성자의 이름은 항상 동일하다.
//    - 어떤 형태로 객체를 생성하는지 알 수 없다.
// 2) 생성자의 오버로딩은 한계가 있다.
// 3) 객체 생성의 정책을 변경할 수 없다.

class User {
    private String nickname;

    private User(String nickname) {
        this.nickname = nickname;
    }

    // 정적 팩토리 메소드
    static User newSubscribingUser(String email) {
        return new User(email.split("@")[0]);
    }

    static User newFacebookUser(int facebookAccountId) {
        return new User(getFacebookName(facebookAccountId));
    }

    static String getFacebookName(int facebookAccountId) {
        return "" + facebookAccountId;
    }
}

public class Sample {
    public static void main(String[] args) {
//        User user1 = new User("chansik.yun@gmail.com");
        User user1 = User.newSubscribingUser("chansik.yun@gmail.com");
//        User user2 = new User(123123123);
        User user2 = User.newFacebookUser(123123123);
    }
}

/*
class User {
    private String nickname;

    User(String email) {
        nickname = email.split("@")[0];
    }

    User(int facebookAccountId) {
        nickname = getFacebookName(facebookAccountId)
    }

    static String getFacebookName(int facebookAccountId) {
        return "" + facebookAccountId;
    }
}

public class Sample {
    public static void main(String[] args) {
        User user1 = new User("chansik.yun@gmail.com");
        User user2 = new User(123123123);
    }
}
*/

























