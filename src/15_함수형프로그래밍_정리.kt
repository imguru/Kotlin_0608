// 15_함수형프로그래밍 정리
package ex15_3

import ex13_2.lastChar2

// 1. 함수형 프로그래밍
//   => 함수를 참조할 수 있다.
//   => 함수의 타입 : 시그니처(인자와 반환타입)에 의해 결정된다.

// 일반 함수
// (Int, Char) -> Double
fun foo(a: Int, b: Char): Double {
    return 3.14
}

// 메소드
//   메소드의 시그니처는 첫번째 인자로 this가 전달된다고 판단해야 한다.
class Dialog {
    // this: 객체의 참조
    // (Dialog, String) -> Unit
    // fun showOff(this: Dialog, message: String)
    fun showOff(message: String) {
        println("I'm dialog - $message")
    }
}

/*
fun main() {
    val dialog = Dialog()
    dialog.showOff("Hello") // showOff(dialog, "Hello")

    val fn2: (Dialog, String) -> Unit = Dialog::showOff  // this가 결정되지 않은 상태
    fn2(dialog, "Hello")

    // 메소드의 this가 이미 결정되어 있다면, this를 제외한 시그니처를 이용할 수 있다.
    val fn3: (String) -> Unit = dialog::showOff  // this가 결정된 상태
    fn3("Hello")               // bound 참조

    val fn: (Int, Char) -> Double = ::foo
    println(fn(10, 'a'))
}
*/


fun lastChar1(message: String): Char {
    return message[message.length - 1]
}

// 확장 함수 - 첫번째 인자로 String 타입의 this가 암묵적으로 전달된다.
// => 기존 클래스의 코드를 수정하지 않고, 메소드를 추가하는 문법
fun String.lastChar2(): Char {
    return this[this.length - 1]
}

// 확장 프로퍼티 - getter / setter 추가(Field가 없는 메소드만 추가 가능하다)
// var: getter + setter
// val: getter

val String.lastChar: Char
    get() = this[this.length - 1]  // getLastChar()

/*
fun main() {
    var result = lastChar1("Hello")
    // "Hello".lastChar()
    println(result)

    result = "Hello".lastChar2()
    println(result)

    result = "Hello".lastChar
    println(result)
}
*/

// fun <T, F> pair(first: T, second: F) = Pair(first, second)
infix fun <T, F> T.pair(second: F) = Pair(this, second)
// 메소드 또는 확장 함수가 인자를 한개만 받을 경우, 중위 함수 호출을 지원할 수 있습니다.

fun main() {
    // val p1 = pair("name", "Tom")
    val p1 = "name".pair("Tom")
    var p2 = "name" pair "Tom"
}








