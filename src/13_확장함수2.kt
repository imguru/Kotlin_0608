package ex13_2

// Java           -> Kotlin
// void foo()     -> fun foo(): Unit

fun lastChar1(text: String): Char = text[text.length - 1]  // (String) -> Char

// 확장 함수 - fun
fun String.lastChar2(): Char = this[this.length - 1]       // (String) -> Char

// 확장 프로퍼티 - val(getter) / var(getter + setter)
//  => Backing Field가 없는 프로퍼티만 가능합니다.
val String.lastChar: Char
    get() {
        return this[length - 1]
    }
// String: Immutable Object - 객체가 생성된 이후에 값이 변경되지 않음.

var StringBuilder.lastChar: Char
    get() {
        return this[length - 1]
    }
    set(value) {
        this[length - 1] = value
    }

/*
fun main() {
    val result = lastChar1("hello")
    val result2 = "hello".lastChar2()     // 확장 함수
    val result3 = "hello".lastChar        // 확장 프로퍼티
    val sb = StringBuilder("hello")
    sb.lastChar = 'x'
    println(sb)
}
*/

fun main() {
    val fullname = "chansik.yun"
    val name = fullname.split(".")[0]
    println(name)
}








