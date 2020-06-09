package ex14

// Infix Function(중위 함수)

// fun pair(first: Any, second: Any) = Pair(first, second)
// infix fun Any.pair(second: Any) = Pair(this, second)

infix fun<T, F> T.pair(second: F) = Pair(this, second)

// 중위 함수: 인자가 하나뿐인 일반 메소드나 인자가 하나뿐인 확장 함수에 대해서 중위 호출을 지원할 수 있습니다.
//   => infix

fun main() {
    // val pair1 = Pair("name", "Tom")
    // val pair2 = Pair("age", 42)

    // val pair1 = pair("name", "Tom")
    // val pair2 = pair("age", 42)

    val pair1 = "name".pair("Tom")
    val pair2 = "age".pair(42)

    val pair3 = "name" pair "Tom"
    val pair4 = "age" to 42

    val map = mapOf(
        pair1, pair2, pair3, pair4
    )
    println(map)
}