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
    companion object {
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

fun main() {
    val user1 = User.newFacebookUser(123123)
    val user2 = User.newSubscribingUser("chansik.yun@gmail.com")
}






