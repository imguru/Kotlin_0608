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
class User(val name: String, val age: Int)

// mapNotNull
//  : 변환의 결과가 null인 것을 filter할 수 있고, 결과타입도 Nullable이 아닙니다.
fun main() {
    val users = listOf(User("Tom", 19), User("Bob", 20))
    val result = users.mapNotNull { user ->
        if (user.age < 20)
            null
        else
            "OK"
    }
}















