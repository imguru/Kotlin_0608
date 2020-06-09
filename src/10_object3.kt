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

    }
}

fun main() {

}


