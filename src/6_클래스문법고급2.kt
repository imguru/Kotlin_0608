package ex6_2

import java.io.*

interface State : Serializable

interface View {
    fun getCurrentState(): State
    fun restoreState(state: State)
}

class Button(var x: Int, var y: Int) : View {

    // Nested Class가 기본입니다.
    // 내부 클래스를 만드는 키워드 - inner
    class ButtonState(val x: Int, val y: Int) : State

    override fun getCurrentState(): State {
        return ButtonState(x, y)
    }

    override fun restoreState(state: State) {
        state as ButtonState

        x = state.x
        y = state.y
    }

    override fun toString(): String {
        return "Button(x=$x, y=$y)"
    }

    @Throws(IOException::class)
    fun foo() {
    }
}

// Kotlin - Checked Exception이 존재하지 않습니다.
//  => 예외 처리가 강제되지 않는다.
fun main() {
    val button = Button(10, 20)
    button.foo()

    val fos = FileOutputStream("button2.dat")
    val oos = ObjectOutputStream(fos)
    oos.writeObject(button.getCurrentState())
    fos.close()
    oos.close()

    button.x = 30
    button.y = 40

    val fis = FileInputStream("button2.dat")
    val ois = ObjectInputStream(fis)
    val s = ois.readObject() as State

    button.restoreState(s)
    println(button)
}













