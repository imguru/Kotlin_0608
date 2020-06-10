// 21_메모이제이션(캐시)
package ex21

import java.util.*

/*
fun fib(k: Int): Long {
    if (k == 0)
        return 1
    if (k == 1)
        return 1

    return fib(k - 1) + fib(k - 2)
}
*/

/*
fun fib(k: Int): Long = when (k) {
    0, 1 -> 1
    else -> fib(k - 1) + fib(k - 2)
}
*/
// 위의 함수는 중복 계산이 많이 발생한다.
// 한번 계산된 결과를 캐시해서, 활용하자. - 메모이제이션(Dynamic Programming, 동적계획법)
//  => 순수 함수(pure function)
//   : 함수의 입력이 동일한 결과도 동일하다.
//   : 참조적 투명성을 보장한다.


// 코틀린에서는 메모이제이션의 기법을 쉽게 적용할 수 있다.
/*
val cache = mutableMapOf<Int, Long>()
fun fib(k: Int): Long = when (k) {
    0, 1 -> 1
    else -> {
        val cached = cache[k]
        if (cached != null)
            cached
        else {
            val result = fib(k - 1) + fib(k - 2)
            cache[k] = result
            result
        }
    }
}
*/
// getOrPut: 메모이제이션을 선언적 형태의 코드로 구현 가능하다.
// val cache = mutableMapOf<Int, Long>()
val cache = WeakHashMap<Int, Long>()
fun fib(k: Int): Long = cache.getOrPut(k) {
    when (k) {
        0, 1 -> 1
        else -> fib(k - 1) + fib(k - 2)
    }
}

// Cache: Map을 통해서 구현을 많이 합니다.
//        일반 Map을 사용하면 주의해야할 것이 있습니다.
//        -> 메모리가 부족할 때까지 무한정 늘어날 수 있습니다.
// WeakHashMap: 약한 참조로 구성된 캐시
//              메모리가 부족할 경우, 자동으로 처리됩니다.

// Reference
//   Strong Ref: GC가 일어나도 수거되지 않는다.
//   Soft Ref: out of memory에 근접하면 수거된다.
//   Weak Ref: GC가 일어나면 수거된다.


fun main() {
    println(fib(10))
    println(fib(30))
    println(fib(100))
}





