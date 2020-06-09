package ex15

import java.util.function.Predicate

// 고차 함수(High Order Function)
// : 함수를 인자로 받거나 함수를 반환하는 함수

// 고차 함수 활용
// => 함수가 없을 때, 자바에서 사용하던 방식

//   Collection 인터페이스에 대해 자바와 다른 설계를 적용하고 있습니다.
//   List<T>         Set<T>          Map<K, V>      - Immutable
//                                                   : 인터페이스의 메소드로 수정 기능이 존재하지 않는다.
//  MutableList<T>  MutableSet<T>  MutableMap<K, V>


// 아래 코드에서 필터의 알고리즘은 동일하지만, 정책은 다르다.
// => "동작 파라미터화"
// => "변하지 않는 전체 알고리즘에서 변해야 하는 정책을 분리한다"
//     변해야 하는 정책 => 인터페이스 기반 정책 클래스
/*
interface Predicate {
    fun test(e: Int): Boolean
}
*/

fun filter(data: List<Int>, predicate: Predicate<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (e in data) {
        if (predicate.test(e))
            result.add(e)
    }
    return result
}

object OddPredicate: Predicate<Int> {
    override fun test(e: Int): Boolean {
        return e % 2 == 1
    }
}

fun main() {
    val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    // val result = filterOdd(list)
    var result = filter(list, OddPredicate)
    println(result)

//    result = filter(list, object: Predicate<Int> {
//        override fun test(e: Int): Boolean {
//            return e % 2 == 0
//        }
//    })

    println(result)
}

/*
fun filterOdd(data: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (e in data) {
        if (e % 2 == 1)
         result.add(e)
    }
    return result
}

fun filterEven(data: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (e in data) {
        if (e % 2 == 0)
            result.add(e)
    }
    return result
}


fun main() {
    val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    // val result = filterOdd(list)
    val result = filterEven(list)

    println(result)
}
*/