package ex10_3

// 3. 익명 객체 - object
interface MouseAdapter {
    fun mouseClicked()
    fun mouseEntered()
}

class Window {
    var mouseAdapter: MouseAdapter? = null

    fun click() {
        // Nullable 타입을 사용하기 전에는 반드시 null이 아님을 보장해야 한다.
        /*
        val adapter = mouseAdapter
        if (adapter != null) {
            adapter.mouseClicked()
        }
        */
        // 위의 방법을 원자적으로 수행하는 문법. - ?.(Safe Call)
        mouseAdapter?.mouseClicked()
    }

    fun enter() {
        mouseAdapter?.mouseEntered()
    }
}

fun foo(window: Window) {
    var a = 0

    // 1. 클로져를 통해 외부 변수를 암묵적으로 참조할 수 있습니다.
    // 2. 클로져를 통해 접근된 변수의 수명은 연장된다.
    window.mouseAdapter = object : MouseAdapter {
        override fun mouseClicked() {
            println("mouseClicked - ${++a}")
        }

        override fun mouseEntered() {
            println("mouseEntered - ${++a}")
        }
    }
}


fun main() {
    val window = Window()
    // 익명 객체를 만드는 방법 입니다.

    foo(window)

    window.enter()
    window.click()
}

// object 정리
// 1) object 선언 - 싱글톤
// 2) companion object - static
// 3) anonymous object





