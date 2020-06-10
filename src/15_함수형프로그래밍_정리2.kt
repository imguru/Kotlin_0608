package ex15_4

// 고차 함수
// : 함수를 인자로 받거나, 함수를 반환하는 함수

// => 함수를 인자로 받는 경우
//    : 변하지 않는 알고리즘에서 변해야 하는 정책을 함수의 참조를 통해 분리한다.
fun filter(data: List<Int>, predicate: (Int) -> Boolean): List<Int> {
    val result = mutableListOf<Int>()

    for (e in data) {
        if (predicate(e))
            result.add(e)
    }

    return result
}

fun isOdd(e: Int) = e % 2 == 1

fun main() {
    val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    var result = filter(list, ::isOdd)

    // 1. 익명 함수
    result = filter(list, fun(e: Int): Boolean {
        return e % 2 == 1
    })

    // 2. 익명 함수 - 단일 표현식
    result = filter(list, fun(e: Int) = e % 2 == 1)

    // 주의 사항: 코틀린은 익명 함수 말고 람다 표현식을 지원합니다.
    // 람다 표현식: 실행 가능한 블록(인자를 받을 수도 있고, 값으로 평가된다.)

    // (Int) -> Boolean
    val isOdd = { e: Int ->
        e % 2 == 1
    }
    // 3. 람다 표현식을 전달할 수 있다.
    result = filter(list, isOdd)

    // 4. 람다 표현식의 인자의 타입을 생략 가능하다. 암시적 추론이 가능하다.
    result = filter(list, { e ->
        e % 2 == 1
    })

    // 5. 함수의 마지막 인자가 함수를 받는 경우, 람다 표현식을 함수 괄호 밖에 둘 수 있다.
    result = filter(list) { e ->
        e % 2 == 1
    }

    // 6. 람다 표현식으로 전달되는 인자가 1개인 경우, 생략 가능하다. - it
    result = filter(list) {
        it % 2 == 1
    }
}









