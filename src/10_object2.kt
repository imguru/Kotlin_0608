package ex10_2
// 10_object2

// 2. Companion object
//   => 정적 메소드 또는 정적 필드를 대체하는 문법입니다.
//   => 코틀린에서는 static 문법이 존재하지 않습니다.

// 아래 문법은 생성자를 private으로 제공하는 방법입니다.
// - constructor 키워드 생략이 불가능한 경우
//  1) 접근 지정자를 변경할 경우
//  2) 어노테이션을 지정할 경우
class User private constructor(val nickname: String) {
    // static -> companion object
    // companion object {

    // 아래의 XXX 이름은 코틀린에서 통용되는 이름이 아닙니다.
    // 자바에서 사용되는 이름입니다.
    // => 중첩 클래스를 통해 구현된다.
    // companion object XXX {
    companion object {
        // private static final String TAG = "UserActivity"
        private const val TAG = "User"
        private const val VALUE = 42

        private val TAG2 = User::class.java.simpleName
        // 상수
        //  - Runtime 상수      : 메모리가 할당되었고, 실행중에 값이 변경되지 않는 상수
        //  - Compile-time 상수 : 메모리가 할당되지 않고, 값으로 존재하는 상수: const
        //   : 상수값, 문자열

        // static field, method
        fun newSubscribingUser(email: String): User {
            val id = email.substringBefore("@")
            return User(id)
        }

        fun newFacebookUser(accountId: Int): User {
            return User("$accountId")
        }
    }
}

/*
fun main() {
    val user1 = User.newFacebookUser(123123)
    val user2 = User.newSubscribingUser("chansik.yun@gmail.com")
}
*/

// 동반 객체 활용 방법

// Map<String, Any>: JSON(Javascript Object Notation)
// Person(name="Tom", age=42)
// ->
// {
//   "name": "Tom",
//   "age": 42
// }

interface MapFactory<T> {
    fun fromMap(map: Map<String, Any>): T
}

// 클래스 타입이 인터페이스를 만족할 수 있다.
data class Person(val name: String) {
    // 인터페이스를 구현하거나 상속을 하는 것이 가능합니다.
    companion object : MapFactory<Person> {
        override fun fromMap(map: Map<String, Any>): Person {
            val name = map["name"] as String
            return Person(name)
        }
    }
}

// 제네릭 함수는 인자를 통해서 T를 추론 가능하다.
// => 제네릭의 복잡도를 낮출 수 있다.
fun <T> loadFromMap(map: Map<String, Any>, factory: MapFactory<T>): T {
    return factory.fromMap(map)
}

fun main() {
    val map: Map<String, Any> = mapOf(
        "name" to "Tom"  // Pair<String, Any>("name", "Tom")
    )

    //                  gson.fromJson(json, Person::class.java)
    val person = loadFromMap(map, Person)
    println(person)
}





















