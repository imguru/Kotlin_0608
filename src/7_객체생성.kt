// 7_객체생성
package ex7

// @JvmOverloads: 자바에서 기본 파라미터값에 대한 부분을 생성자 오버로딩을 통해 처리할 수 있도록 한다.
class Person @JvmOverloads constructor(val name: String, val age: Int, val address: String = "", val school: String = "")

// 핵심: 코틀린에서는 Builder를 더 이상 권장하지 않습니다.
fun main() {
    // val user = Person(name = "Tom", age = 42, address = "Suwon", school = "Seoul")
    val user = Person(name = "Tom", age = 42)
}