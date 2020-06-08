package ex6
// 6_클래스문법고급

interface Clickable {
    // Clickable의 인터페이스를 구현하는 모든 클래스는 반드시 name의 프로퍼티를 정의해야 한다.
    val name: String
    // val: getter
    // var: getter + setter

    fun click()

    fun showOff() {
        println("Clickable!")
    }
}

interface Focusable {
    fun focus()

    fun showOff() {
        println("Focusable!")
    }
}

// Interface는 기본 구현을 가질 수 있다.
// Interface는 여러 개를 구현할 수 있다.
//  => 동일한 구현이 제공된다면, 어떤 구현으로 선택할 지 알 수 없다. - 모호성 에러(다이아몬드 상속)
// class Button implements Clickable implements Focusable

// class Button
// => public final class Button
// => 코틀린의 모든 클래스는 기본적으로 상속이 금지되어 있습니다.
//  : Effective Java
//   => 기반 클래스를 변경하는 경우, 하위 클래스의 동작이 예기치 않게 변경될 수 있다.
//   => 상속을 위한 설계와 문서를 갖추거나, 그럴 수 없다면 상속을 금지해라.

// 상속을 허용한다. - open
// open class Button : Clickable, Focusable {
abstract class Button : Clickable, Focusable {
    // interface 또는 부모의 프로퍼티를 재정의하였다.
    // override val name: String = ""
    override val name: String
        get() {
            return "Tom"
        }

    override fun showOff() {
        super<Clickable>.showOff()   // 부모의 메소드를 호출하는 방법
        super<Focusable>.showOff()
    }

    override fun click() {

    }

    override fun focus() {

    }

    // public final void foo() {}
    // final: 오버라이딩 금지 - Java
    // open: 오버라이딩 허용  - Kotlin
    // open fun foo() {}
    abstract fun foo()
}

// class MouseButton extends Button

// class MouseButton : Clickable   -> 구현
// class MouseButton : Button()    -> 상속

class MouseButton : Button() {
    override fun foo() {

    }
}

fun main() {

}








