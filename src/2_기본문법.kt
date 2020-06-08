// 2_기본문법.kt

// 1. main 함수를 만드는 방법
// fun main() {
// fun main(args: Array<String>) {

// 2. 함수를 만드는 방법
// fun 함수이름(파라미터_이름: 파라미터_타입) : 반환타입
// => 전역 함수를 만들 수 있다.
//   Java: Collections, Arrays, Objects ...
//     => private 생성자 / public static 함수의 집합
//   Kotlin
//     => Utils.kt, Arrays.kt

// 3. 객체 지향 언어 + 함수형 언어
//    => 순수 함수(pure function)이어야 한다.
//    => 함수의 입력이 동일하면, 함수의 결과도 동일해야 한다.
//    => void(Java) -> Unit(Kotlin)

/*
fun add(a: Int, b: Int): Int {
    return a + b
}

// fun foo(): Unit
fun foo() {
}

fun main() {
    println(foo())
}
*/
// 4. 타입 시스템
//  - Java - 약한 타입 언어
//   * Primitive Type - int, double, char, byte ..
//     1) Value Type - 복사
//     2) Collection에 저장할 수 없습니다. - Wrapper Class(Integer, Double, Char, Byte ...)
//     3) 객체가 아니다. - 필드와 메소드를 가질 수 없다.

//   * Reference Type - class, enum, interface, array
//     1) Reference Type - 참조
//     2) 객체이다.

//  - Kotlin - 강한 타입 언어
//    * 모든 타입은 객체 타입이다.
//      : 필드와 메소드를 가질 수 있다.
//      => 코틀린 컴파일러가 원시 타입으로 사용할지, 참조 타입으로 사용할지 판단하기 때문에, 성능 저하가 최소화된다.
//    * 강력한 타입 제약이 존재합니다.
//      : 암묵적인 타입 변환을 거의 허용하지 않습니다.
//        int <-> long / double <-> float
//        .toString() -> String
//        .toXXX()    -> XXX
//    * 변수를 선언하는 방법
//     Java
//     val - final T a;
//     var - T a;
//     1) val
//      : final
//     2) var
//      : 일반적으로 값을 변경할 수 있다.
/*
fun main() {
    // 암묵적 타입 선언
    val a = 42
    // a = 30  // Failed - val

    // 명시적 타입 선언
    var b : Long = a.toLong()
    b = 64

    // ... 100줄

    println(b)
    // 42.toDouble()
}
*/

// 5. 기본 연산자 - Bit Operators
// Java
//   &, |, >>, <<, >>>, ^, ~
fun main() {
    // 2진수 리터럴 문법
    val bit = 0b11000011
    println(bit)

    println(bit shr 1)   // bit >> 1  (산술 쉬프트)
    println(bit ushr 1)  // bit >>> 1 (논리 쉬프트)
    println(bit shl 1)   // bit << 1

    println(bit and 1)   // bit & 1
    println(bit or 1)    // bit | 1
    println(bit xor 1)   // bit ^ 1
    println(bit.inv())   // ~bit
}















