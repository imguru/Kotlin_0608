package ex14

import java.lang.StringBuilder

/*
fun <T> joinToString(
    collection: Collection<T>,
    seperator: String,
    prefix: String,
    postfix: String
): String {
    val result = StringBuilder(prefix)

    // Loop에서 index 정보가 필요합니다.
    for ((index, element) in collection.withIndex()) {
        if (index > 0)
            result.append(seperator)
        result.append(element)
    }
    result.append(postfix)
    return result.toString()
}
*/

// 1. Collection<T>. - 수신 타입 지정
// 2. 기존의 첫번째 인자의 변수명을 this로 변경하면 된다.
fun <T> Collection<T>.joinToString(
    seperator: String = ", ",
    prefix: String = "[",
    postfix: String = "]"
): String {
    val result = StringBuilder(prefix)

    // for ((index, element) in this.withIndex()) {
    for ((index, element) in withIndex()) {
        if (index > 0)
            result.append(seperator)
        result.append(element)
    }
    result.append(postfix)
    return result.toString()
}

fun main() {
    val people = listOf("Tom", "Bob", "Alice")
    // val result = joinToString(people, ", ", "[", "]")

    // val result = people.joinToString(", ", "[", "]")
    val result = people.joinToString()

    println(result) // ["Tom", "Bob", "Alice"]
}