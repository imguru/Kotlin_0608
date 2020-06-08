package ex6
// 6_클래스문법고급

interface Clickable {
    // Clickable의 인터페이스를 구현하는 모든 클래스는 반드시 name의 프로퍼티를 정의해야 한다.
    val name: String

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
class Button : Clickable, Focusable {
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
}




