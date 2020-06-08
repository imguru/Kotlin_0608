package ex10

// object
// 1. object 선언 - Singleton
object Cursor {
    init {
        println("Cursor created")
    }

    fun move() {
        println("Cursor moved")
    }
}

fun main() {
    println("main()")
    Cursor.move()
}