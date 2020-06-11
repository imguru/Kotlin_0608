package ex25

// 프로퍼티: getter + setter 생성하는 기술

class User {
    var fullName: String
        get() = "fullname"
        set(value) {
            println("xxx - $value")
        }

    var name: String = ""
        get() {
            println("xxx")
            return field
        }
        set(value) {
            println("xxx")
            field = value
        }

    var age: Int = 0
}

// equals / hashCode
// toString
// copy
// componentN: 구조 분해 선언
data class Person(val name: String, val age: Int)

fun main() {
    val person = Person("Tom", 42)
    println(person)

    val copy = person.copy()
    println(person)

    val (name, age) = person


    val user = User()
    user.name = "Tom"   // user.setName("Tom")
    println(user.name)  // user.getName()
}

// object 용도
// 1. object 선언 - 싱글톤
// 2. companion object - Java's static
// 3. anonymous object

// 위임
// 1. 클래스 위임 - 메소드 위임
// 2. 프로퍼티 위임 - getter / setter의 위임
//    lazy(val) / KVO / KVC / vetoable
//    lateinit(var) - Android X

// 함수형 프로그래밍
// 1. 함수를 참조하는 방법
//    함수의 시그니처 - 함수의 인자와 함수의 반환 타입 정보
//      일반함수 vs 메소드(첫번째 인자로 객체의 참조가 this로 암묵적으로 전달된다.)
// 1. foo       - (Int, Char) -> Double       (일반 함수)
// 2. Goo::goo  - (Goo, Int, Char) -> Double  (메소드)
// 3. val g = Goo()                           (this가 결정된 메소드)
// g::goo    - (Int, Char) -> Double
fun foo(a: Int, b: Char): Double = 3.14
class Goo {
    fun goo(a: Int, b: Char): Double = 3.14
}

// 확장 함수
fun lastChar1(text: String): Char? = text.lastOrNull()
// lastChar("Hello")

fun String.lastChar2(): Char? = /* this.*/ lastOrNull()
// "Hello".lastChar2()

// 확장 프로퍼티
val String.lastChar: Char?
    get() = lastOrNull()
// "Hello".lastChar

// 고차 함수
// 1. 함수를 인자로 받는 경우 - 정책을 전달할 때
// 2. 함수를 반환하는 함수 - 실행 시간에 함수를 생성할 때
// 3. 함수 합성
// 4. 커링
//   => 부분 적용: 함수의 인자를 고정해서 새로운 함수를 만드는 방법
//      지연 호출: 함수의 결과를 지연할 수 있다.
fun sum(a: Int, b: Int) = a + b
// sum(a, b)
// => sum(a)(b)

fun sum(a: Int): (b: Int) -> Int = { b ->
    a + b
}
// sum(a)(b)

// inline
// => 함수를 호출하는 것이 아니라, 치환하는 것이다.
// => 함수 호출의 오버헤드를 제거할 수 있다.
// => 전체 코드 메모리 사용량이 늘어날 수 있다.
// 1) 함수의 인자로 함수를 받는 경우
// 2) T의 타입 정보를 유지하는 함수를 만드는 경우 - reified

// 함수가 인자로 함수를 전달 받을 때
// 1) 익명 함수
//    return: 지역 반환
//    return@외부함수이름: 비지역 반환
// 2) 람다 표현식
//    return: 비지역 반환
//    return@내부함수이름: 지역 반환

val cache = mutableMapOf<Int, Long>()
fun fib(k: Int): Long = cache.getOrPut(k) {
    when (k) {
        0, 1 -> 1
        else -> fib(k - 1) + fib(k - 2)
    }
}

// Nullable
//  Int?

// 1. Smart cast
//   : null check를 하면, 이후에 Int? -> Int로 취급된다.
//     var 프로퍼티에 대해서는 사용할 수 없다.

// 2. ?.let
//   : var 프로퍼티에 대해서도 사용할 수 있다.

// 3. ?:
//    Elvis Operator

// 범위 지정 함수
//  - let, apply, with, use

// Sequense API
//  - map, flatMap, filter, groupBy, first, last, take, drop

// Kotlin in action