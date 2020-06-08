// 4_접근지정자
//   * Java
//     private - package - protected - public
//  => 잘못 설계된 점
//    1) package level - module
//     : 다른 모듈에서 같은 패키지이름 만들면 접근이 가능하다.
//    2) protected
//     : 같은 패키지에서 protected에 접근 가능하다.

//   * Kotlin
//     private - internal(모듈) - protected - public
//  - internal: 실제 package level을 사용합니다.
//            컴파일시 패키지 이름을 맹글링 합니다. (JDK 8)
//            JDK 9(Java 9) - module을 이용하는 형태로 변경될 예정입니다.
//  - protected: 오직 자식을 통해서만 접근 가능하다.

// IntelliJ(Android Studio)
//   Project
//     - module : com.lge
//                com.google.Sample - 정보 은닉 실패!

//     - module : com.google
/*
class Point {
    protected var x: Int = 0
    protected var y: Int = 0
}

fun main() {
    val point = Point()
    // point.x = 42
}
*/

// public class Foo
// 다른 모듈에서도 접근 가능한 레벨
class Foo

// 같은 모듈에서만 접근 가능한 레벨
internal class Goo

// 같은 파일에서만 접근 가능한 레벨 - fileprivate
private class Hoo

// public fun foo
fun foo() {

}
internal fun goo() {
}

private fun hoo() {
}



















