package ex17

// 함수 합성(Function Composition)
// : 함수를 인자로 받아서, 함수를 반환하는 함수
// F(x) -> y
// G(y) -> z
// F + G => H(x) -> z

/*
fun compose(f: (String) -> Int, g: (Any) -> Int) : (String) -> Int = { x ->
    val y = f(x)
    val z = g(y)
    z
}
*/

// Step 1. Generic
/*
fun<X, Y, Z> compose(f: (X) -> Y, g: (Y) -> Z): (X) -> Z = { x ->
    g(f(x))
}
*/

// Step 2. Extension Function
// Step 3. infix
infix fun <X, Y, Z> ((X) -> Y).compose(g: (Y) -> Z): (X) -> Z = { x ->
    g(this(x))
}

fun main() {
    val f: (String) -> Int = String::length
    val g: (Any) -> Int = Any::hashCode
    // f + g = 문자열의 길이를 기반으로 해시값을 구하는 함수
    // => (String) -> Int

    // val fog = compose(f, g)
    // val fog = f.compose(g)
    val fog = f compose g

    val r1 = fog("hello")
    val r2 = fog("oellh")

    println("$r1, $r2")
}
