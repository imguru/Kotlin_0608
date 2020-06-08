package ex8

import java.lang.IllegalArgumentException

// 봉인된 클래스
// soft keyword: 단독으로 사용되었을 때는 의미가 부여되지 않는 키워드

// open class
// abstract class
// enum class

/*
enum class Color {
    RED, ORANGE, YELLOW, INDIGO   // 객체
}
*/

// enum 객체는 객체이기 때문에 속성과 메소드를 가질 수 있습니다.
enum class Color(val r: Int, val g: Int, val b: Int) {
    RED(255, 0, 0),
    ORANGE(255, 155, 0),
    YELLOW(255, 255, 0),
    INDIGO(75, 0, 130);   // 영역 구분을 위해 반드시 ; 이 필요합니다.

    val rgb: Int
        get() {
            return (r * 256 + g) * 256 + b
        }

    fun color(): Int {
        return (r * 256 + g) * 256 + b
    }
}

// Kotlin 에서는 switch-case 문법이 존재하지 않습니다.
//  => when
fun getName(color: Color): String {
    // 1. break 하지 않아도 됩니다.
    // 2. Expression(식) vs Statement(문)
    //     => 식은 결과가 존재하지만, 문은 결과가 존재하지 않는다.
//    when (color) {
//        Color.RED -> return "Red"
//        Color.ORANGE -> return "Orange"
//        Color.YELLOW -> return "Yellow"
//        Color.INDIGO -> return "Indigo"
//    }

    // if도 식입니다.
    //  -> Java 에서는 삼항 연산자를 통해 대부분 처리하였습니다.
    //  -> Kotlin 에서는 삼항 연산자가 문법이 존재하지 않습니다.
    val a = if (color == Color.RED)
        "Red"
    else
        "Unknown"

//    return when (color) {
//        Color.RED -> "Red"
//        Color.ORANGE -> "Orange"
//        Color.YELLOW -> "Yellow"
//        Color.INDIGO -> "Indigo"
//    }

    // else -> default 구문과 동일합니다.
    // {} -> 결과가 존재합니다.
    //     : 마지막 구문의 결과가 블록의 결과입니다.
    return when (color) {
        Color.RED, Color.ORANGE -> {
            val r = "Red"
            r
        }
        Color.YELLOW -> "Yellow"
        else -> "Indigo"
    }
}

/*
fun main() {
    println(getName(Color.RED))

    val color: Color = Color.RED
    println(color.r)
    println(color.rgb)
    println(color.color())
}
*/

/*
interface Expr
class Num(val value: Int) : Expr
class Sum(val left: Expr, val right: Expr) : Expr

// 아래 코드의 문제점
// : 새로운 Expr 기반의 클래스가 생성될 경우, 예외가 발생할 수 있다.
// 해결 방법
// : Num, Sum 이외의 하위 클래스를 막을 수 있으면 된다.
//  => 봉인된 클래스: 계층 확장 제한 문법
fun eval(e: Expr): Int {
    return when (e) {
        is Num -> e.value
        is Sum -> eval(e.left) + eval(e.right)
        else -> throw IllegalArgumentException("Unknown expression")
    }
}

fun main() {
    val l = Num(10)
    val r = Num(30)
    val e = Sum(l, r)

    val result = eval(e)
    println(result)
}
*/

sealed class Expr {
    // 중첩 클래스를 통해 제공해야 한다.(1.0) - 최신 코틀린에서는 제약이 완화되었다.
    // => 같은 파일내에서도 상속할 수 있다. (1.1)
}

class Num(val value: Int) : Expr()
class Sum(val left: Expr, val right: Expr) : Expr()

// 1. Expr은 봉인된 클래스이기 때문에, 자식 클래스가 Num, Sum 밖에 존재하지 않는 다는 것을 컴파일러는 알 수 있다.
fun eval(e: Expr): Int {
    return when (e) {
        is Num -> e.value
        is Sum -> eval(e.left) + eval(e.right)
    }
}










