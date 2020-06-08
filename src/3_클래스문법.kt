// 3_클래스문법
/*
public class User {
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
*/

// 1. public class가 기본 접근 지정자 입니다.
// 2. 생성자 함수는 constuctor 라는 키워드를 통해 약속되어 있습니다.
/*
class User {
    private var name: String
    private var age: Int

    // Primary Constructor(주 생성자)
    //  : 모든 필드를 온전하게 초기화하는 생성자
    constructor(name: String, age: Int) {
        this.name = name
        this.age = age
    }
}
*/

/*
class User constructor(
    private var name: String,
    private var age: Int
)
*/

class User(
    private var name: String,
    private var age: Int
)

fun main() {
    // Java
    // User user = new User("Tom", 42);

    // Kotlin - new 키워드가 사라졌습니다.
    var user1 = User("Tom", 42)
    var user2 = User("Tom", 42)

    // 객체 비교
    // 1) 참조 동등성 - 같은 참조를 갖는가?
    //   Java: ==
    // 2) 객체 동등성 - 같은 값을 갖는가?
    //   Java: .equals


}









