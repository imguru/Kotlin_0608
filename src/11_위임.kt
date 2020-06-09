package ex11

// 위임
//  1. 객체의 하나 이상의 메소드 호출을 다른 객체로 '위임'한다.
//  => 포함을 구현할 때, 위임에 대한 코드를 암묵적으로 생성하는 문법

interface UIElement {
    fun getHeight(): Int
    fun getWidth(): Int
}

open class Rectangle(val x1: Int, val x2: Int, val y1: Int, val y2: Int) : UIElement {
    override fun getHeight(): Int {
        return y2 - y1
    }

    override fun getWidth(): Int {
        return x2 - x1
    }
}

// 객체 지향 프로그래밍에서 기존 클래스를 재활용하는 방법
// 1. 상속 - 권장되지 않음
// class Panel(x1: Int, x2: Int, y1: Int, y2: Int) : Rectangle(x1, x2, y1, y2)
// 장점: 암묵적으로 부모의 인터페이스와 구현을 물려받을 수 있다.
// 단점: 부모 클래스와 강력한 결합의 형성된다.
//      유지보수에 악영향이 있다.

// 2. 포함
// 장점: 자식 클래스의 구현을 쉽게 변경할 수 있다.
// 단점: 위임에 대한 코드를 직접 작성해야 한다.
/*
class Panel(val rectangle: Rectangle) : UIElement {
    override fun getHeight(): Int {
        return rectangle.getHeight()
    }

    override fun getWidth(): Int {
        return rectangle.getWidth()
    }
}
*/
class Panel(rectangle: UIElement) : UIElement by rectangle

fun main() {
    val r = Rectangle(10, 20, 30, 40)
    val panel = Panel(r)  // Dependecy Injection

//    val panel = Panel(10, 20, 30, 40)
    println(panel.getWidth())
    println(panel.getHeight())
}









