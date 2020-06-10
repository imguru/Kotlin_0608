package ex18

import java.lang.Appendable
import java.time.LocalDateTime

// 커링(Currying)
// : 다중 인수를 갖는 함수를 단일 인수를 갖는 함수들의 함수열로 바꾸는 작업

//                    커링
// sum(10, 20)         ->       sum(10)(20)
// (Int, Int) -> Int            (Int) -> ((Int) -> Int)
//                           => (Int) -> (Int) -> Int
//                              코틀린의 함수 표현의 ->는 오른쪽부터 결합한다.

// 일반 함수
fun sum(a: Int, b: Int): Int = a + b

//fun sum(a: Int): (b: Int) -> Int = { b: Int ->
//    a + b
//}

// 인자가 2개인 함수에 대해서, 커링을 자동으로 생성하는 함수를 만들어봅시다.
// (P1, P2) -> R

fun <P1, P2, R> ((P1, P2) -> R).curried(): (P1) -> (P2) -> R = { p1 ->
    { p2 ->
        this(p1, p2)
    }
}

/*
fun main() {
    val csum = ::sum.curried()

    val r2 = csum(10)(20)
    println(r2)

    // val r1 = sum(10, 20)
    // println(r1)
}
*/

fun <P1, P2, P3, R> ((P1, P2, P3) -> R).curried(): (P1) -> (P2) -> (P3) -> R = { p1 ->
    { p2 ->
        { p3 ->
            this(p1, p2, p3)
        }
    }
}

// 부분 적용 - 함수의 인자를 특정 값으로 고정할 수 있다.(bind)
fun compute(logger: (String) -> Unit) {
    logger("계산이 시작되었습니다.")
    logger("계산 중입니다.")
    logger("계산이 완료되었습니다.")
}

enum class Level { INFO, WARN, ERROR }

// 현재 사용하고 있는 로그 함수
fun log(level: Level, appendable: Appendable, message: String) {
    appendable.appendln("[${level.name}][${LocalDateTime.now()}]: $message")
}

fun main() {
    // 목적: compute 함수에 현재 log를 적용하고 싶다.
    compute { message ->
        log(Level.INFO, System.out, message)
    }

    // 1. 인자를 고정한다 - 부분 적용(bind)
    // 2. 함수 호출을 지연한다.
    val logger = ::log.curried()(Level.WARN)(System.out)
    compute(logger)
}








