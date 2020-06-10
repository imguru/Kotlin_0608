package ex18

// 커링(Currying)
// : 다중 인수를 갖는 함수를 단일 인수를 갖는 함수들의 함수열로 바꾸는 작업

//                    커링
// sum(10, 20)         ->       sum(10)(20)
// (Int, Int) -> Int            (Int) -> ((Int) -> Int)
//                              => (Int) -> (Int) -> Int
//                              코틀린의 함수 표현의 ->는 오른쪽부터 결합한다.

// 일반 함수
fun sum(a: Int, b: Int) : Int = a + b

fun sum(a: Int): (Int) -> Int = { b ->
    a + b
}


fun main() {
    val r1 = sum(10, 20)
    println(r1)
}