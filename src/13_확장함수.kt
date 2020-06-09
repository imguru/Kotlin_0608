// 13_확장함수
// : 중요합니다.
//   코틀린 라이브러리의 핵심 구현 원리
package ex13

// OCP - Open Close Principle(개방 폐쇄의 원칙)
//  : 수정에는 닫혀 있고, 확장에는 열려 있어야 한다.
//  => 새로운 기능을 추가하여도, 기존 코드는 수정되면 안된다.

// '상속'을 통한 확장 - 수직 확장
// 1) 깨지기 쉬운 기반 클래스의 문제
// 2) 상속이 불가능한 객체에 대해서는 확장이 불가능하다.

// 수평 확장
// : 새로운 기능의 추가를 기존 클래스의 수정 없이 가능하다.
// C# - Extension Method
// Swift - Extension
// ObjC - Category
// JS - Prototype
// => Kotlin: Extension Function

class User {
    var name = ""

    // User 객체의 참조(this)가 암묵적으로 전달된다. - 메소드
    // fun foo(this: User) {}
    fun foo() {
        println(name)
        // println(this.name)
    }
}

// fun lastChar(text: String): Char = text[text.length - 1]
// -> lastChar("hello")

// 아래 코드의 의미는 lastChar의 첫번째 인자로 String 타입의 this가 전달될 것이다.
fun String.lastChar(): Char = this[length - 1]
// -> "hello".lastChar()

//  get(index: Int) -> []       // text.get(text.length - 1)

fun main() {
    val user = User()
    user.foo()          // foo(user)

    val text = "Hello"

    // val lc = lastChar(text)
    val lc = text.lastChar()
    println(lc)
}








