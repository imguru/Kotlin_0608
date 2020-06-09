package ex12

// 함수형 프로그래밍
// => 함수를 일급 시민(First-class citizen)으로 취급한다.
// => 함수를 참조할 수 있다.
// 1) 함수를 변수에 담을 수 있다.
// 2) 함수를 반환할 수 있습니다.
// 3) 함수를 인자로 전달 받을 수 있습니다.
// 4) 함수를 실행 시간에 생성할 수 있습니다.
// 5) 함수를 익명으로 생성할 수 있습니다.
//  => Kotlin, Swift, Javscript, C#, C++

fun add1(a: Int, b: Int): Int {
    return a + b
}

// 단일 표현식 함수 - 타입 추론이 가능하다.
// => 반환값에 대한 추론 뿐 아니라, 우항의 타입 정보에 대한 추론도 가능하다.
fun add2(a: Int, b: Int): Int = a + b


// 함수의 타입
//  : 함수의 타입은 '함수의 시그니처'에 의해 결정된다.
//  시그니처: 함수의 반환타입과 함수의 인자 타입에 대한 정보

fun add(a: Int, b: Int): Int = a + b  // (Int, Int) -> Int
fun sub(a: Int, b: Int): Int = a - b  // (Int, Int) -> Int
fun say(message: String) = println("Hello, $message") // (String) -> Unit
fun test(a: Int, b: Char): Double = 3.14  // (Int, Char) -> Double

fun main() {
    // 함수를 참조하는 변수 - 함수 포인터 개념과 동일합니다.
    // :: <- 전역 이름 공간
    // KFunctionN<Arg1, Arg2, ReturnType>
    // N: 인자의 개수
    //  -> 직접 접근할 수 있는 타입이 아닙니다.
    //     Kotlin에서 함수를 사용하면, JVM에 해석되는 타입입니다.
    var fp: (Int, Int) -> Int = ::add
    fp = ::sub

    var result = fp(10, 20)
    println(result)

    fp = ::add
    result = fp(10, 20)

    println(result)
}




















