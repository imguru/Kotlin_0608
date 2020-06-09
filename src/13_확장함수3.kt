package ex13_3

open class View {
    open fun showOff() = println("I'm View")        // 1
}

class Button : View() {
    override fun showOff()  = println("I'm Button") // 2
}

// 부모의 참조 타입으로 자식 객체를 참조하는 것이 가능하다.
// => 암묵적인 Upcasting을 허용한다.

// 함수 바인딩
// 1) 정적 바인딩 - 컴파일러가 어떤 함수를 호출할 지 결정하는 것
// 2) 동적 바인딩 - 런타임에 객체의 값을 확인해서 결정하는 것

fun main() {
    val view: View = Button()
    view.showOff()
}

