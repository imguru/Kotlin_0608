// 코틀린 표준 라이브러리
package ex24

import java.io.File
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException

// 1. 조건 확인 함수
// check -> IllegalStateException
// require -> IllegalArgumentException

//  안드로이드 라이브러리에서 아래 기능을 제공한다.(Fragment)
// => checkActivity
// => requireActivity
/*
fun logMessage(filename: String, message: String) {
    val f = File(filename)
    /*
    if (!f.exists()) {
        throw IllegalStateException("")
        // or
        throw IllegalArgumentException("")
    }
    */
    check(f.exists())
    require(f.exists())
}

fun main() {
    logMessage("a.txt2", "hello")
}
*/

// 2. 명시적인 종료 함수
// TODO: NotImplementedError
// error: IllegalStateException
interface Clickable {
    fun click()
}

class Button : Clickable {
    override fun click() {
        // TODO("Not yet implemented")
        error("Error!")
    }
}


// 3. 코틀린은 '컬렉션을 다루는 많은 함수'를 제공합니다.
//  => Sequence API
//  => Java는 위의 기능을 Stream API 라는 이름으로 제공합니다. - Java 8
//   ; 안드로이드에서 자바 8에서 추가된 라이브러리를 온전하게 이용하기 위해서는 minimum SDK(23) 버전 이상 되어야 합니다.


// 1. map(transform): 변환
//  : 각 원소에 동일한 연산을 수행해서 나온 결과로 새로운 컬렉션을 만드는 함수
/*
fun main() {
    val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

//    val fn: (Int) -> Int = { e: Int ->
//        e * 2
//    }
    val fn: (Int) -> Char = { e: Int ->
        'A' + (e - 1)
    }
    val result = list.map(fn)
    val result2 = list.map { e ->
        e + 10
    }

    println(list)
    println(result2)
}
*/
data class User(val name: String, val age: Int)

// 2. mapNotNull
//  : 변환의 결과가 null인 것을 filter할 수 있고, 결과타입도 Nullable이 아닙니다.
//    filterNotNull
//  : Null이 아닌 것으로 새로운 컬렉션을 생성해준다.
/*
fun main() {
    val users = listOf(User("Tom", 19), User("Bob", 20))
    val result = users.map { user ->
        if (user.age < 20)
            null
        else
            "OK"
    }.filterNotNull()
}
*/

// 3. flatMap
// [ 1, 2, 3 ]
//     =>
//              [
//   1 ->      [ 1 ]
//   2 ->      [ 1, 2 ]
//   3 ->      [ 1, 2, 3 ]
//              ]

// map     =>  [[1], [1, 2], [1, 2, 3]]
// flatMap =>  [ 1, 1, 2, 1, 2, 3 ]
/*
fun main() {
    val list = listOf(1, 2, 3)
    val result = list.flatMap { n ->
        val r = mutableListOf<Int>()
        for (e in 1..n)
            r.add(e)
        r.toList()
    }
    println(result)
}
*/

// 4. groupBy
/*
fun main() {
    val list = listOf(
        User("Tom1", 10),
        User("Tom2", 20),
        User("Tom3", 12),
        User("Tom4", 14),
        User("Tom5", 16),
        User("Tom6", 32),
        User("Tom7", 40),
        User("Tom8", 60)
    )
    // 10대 ~ 60대까지 구분하고 싶다.
    // 10대 =>  [ ]
    // 20대 =>  [ ]
    // : Map<String, List<User>>

//    val result = list.groupBy { e ->
//        if (e.age in 10..19)
//            "10대"
//        else if (e.age in 20..29)
//            "20대"
//        else if (e.age in 30..39)
//            "30대"
//        else
//            "나이듬"
//    }

    val result = list.groupBy { e ->
        when (e.age) {
            in 10..19 -> "10대"
            in 20..29 -> "20대"
            in 30..39 -> "30대"
            else -> "나이듬"
        }
    }

    println(result)
}
*/

// 5. 데이터를 추출하는 연산
//  - take / takeLast: 원하는 요소를 추출
//  - drop / dropLast: 원하지 않는 요소를 제거

//  - first / firstOrNull
//  - last / lastOrNull

// 6. distinct: 중복을 제거한다.
fun main() {
    val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    list.map {
        it % 5
    }.distinct().forEach {
        println(it)
    }


    // val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    // val list = emptyList<Int>()
    // val e = list[0] // Out of Index: list가 비어있을 경우 - IndexOutOfBoundsException

    // val e = list.firstOrNull()

//    list.takeLast(5).forEach {
//        println(it)
//    }

    list.dropWhile {
        it < 5
    }.forEach {
        // println(it)
    }

}














