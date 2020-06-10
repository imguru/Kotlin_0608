package ex16
// 16_고차함수
// 고차 함수
// 1. 인자로 함수를 받는 경우
//  : 다양한 정책에서 동작하는 함수의 코드 중복을 없앨 수 있다.
// 2. 인자로 함수를 반환하는 경우
//  : 런타임에 함수를 생성할 수 있다.

// fun isEven(e: Int) = e % 2 == 0
// fun isOdd(e: Int) = e % 2 == 1

// isEven 또는 isOdd 함수를 실행 시간에 생성하는 방법
// 목표: e를 k로 나눴을 때 r이 나오는지 체크하는 함수를 만드는 함수 => modulo
//  => Closure 이용하는 방법
/*
fun modulo(k: Int, r: Int) : (Int) -> Boolean {
    return { e: Int ->

        e % k == r
    }
}
*/

fun modulo(k: Int, r: Int): (Int) -> Boolean = { e: Int ->
    e % k == r
}

fun main() {
    val isEven = modulo(2, 0)
    val isOdd = modulo(2, 1)

    val list = listOf(1, 2, 3, 4, 5, 6, 7)
    var result = list.filter(isEven)
    println(result)

    result = list.filter(modulo(2, 1))
    println(result)
}

/*
fun foo(): (String) -> String {
    return { s ->
        s.reversed()
    }
}

fun foo2(): (String) -> String = { s ->
    s.reversed()
}

fun main() {
    val fn: (String) -> String = foo()

    val result = fn("hello")
    println(result)
}
*/
















