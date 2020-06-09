package ex15_2

// Kotlin의 방식 - '인터페이스' 아닌 '함수'를 통해 정책을 분리합니다.
// 고차 함수
// 1. 인자로 함수를 받는 경우
//  : 다양한 정책에서 동작하는 함수의 코드 중복을 없앨 수 있다.

fun isOdd(e: Int) = e % 2 == 1   // (Int) -> Boolean
fun isEven(e: Int) = e % 2 == 0  // (Int) -> Boolean

fun filter(data: List<Int>, test: (Int) -> Boolean): List<Int> {
    val result = mutableListOf<Int>()
    for (e in data) {
        if (test(e))
            result.add(e)
    }
    return result
}

fun main() {
    val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    var result = filter(list, ::isEven)
    println(result)

    result = filter(list, ::isOdd)
    println(result)

    result = list.filter(::isOdd)
    println(result)

    // 1. 익명 함수를 지원합니다.
    result = list.filter(fun(e: Int): Boolean {
        return e % 2 == 0
    })

    // 2. 익명 함수 = 단일 표현식으로 표현하는 것도 가능합니다.
    result = list.filter(fun(e: Int) = e % 2 == 0)

    // 3. 주의: 코틀린은 익명 함수와 람다가 다릅니다.
    //  Lambda 표현식: 실행 가능한 코드 블록을 참조하는 기술
    //  코틀린은 별도의 람다 표현식 문법이 존재합니다.
//    val isEven = { e: Int ->
//        e % 2 == 0
//    }
    val isEven: (Int) -> Boolean = { e ->
        e % 2 == 0
    }
    result = list.filter(isEven)

    result = list.filter({ e ->
        e % 2 == 0
    })

    // 만약 함수가 마지막 인자로 함수를 전달받을 경우, 람다를 사용하면, 람다 블록의 표현식을 함수 호출의 괄호 밖에 작성하는 것이
    // 가능하다.
    result = list.filter() { e ->
        e % 2 == 0
    }

    // 함수의 인자 괄호가 존재하지 않는 경우, 괄호도 생략 가능하다.
    result = list.filter { e ->
        e % 2 == 0
    }

    // 람다 블록의 인자가 1개인 경우, 이름을 지정하지 않으면, it으로 자동으로 바인딩 된다.
    result = list.filter {
        it % 2 == 0
    }







}




