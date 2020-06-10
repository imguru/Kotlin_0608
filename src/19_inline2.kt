package ex19_2

// 아래 같은 함수를 자바에서는 절대 제공할 수 없습니다.
// T
// C++ Template: 코드를 생성한다.
// Java Generic: 코드를 생성하는 것이 아니라, 컴파일러가 타입 체크 목적으로만 사용하고, 런타임에서는 T를 Any로 취급한다.

// inline 용도
// 2) reified T

inline fun <reified T> isA(value: Any) = value is T

fun main() {
    var a: Any = "hello"
    a = 30
    // if (a is String) {
    if (isA<String>(a)) {
        println("String")
    }

    if (isA<Int>(a)) {
        println("Int")
    }
}