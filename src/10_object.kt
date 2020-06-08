package ex10

import ex10.Cursor.move
import java.io.File
import com.lge.ex10.Cursor as JCursor

// import alias - 동일 패키지 내에 이름의 충돌을 방지하기 위해 다른 이름으로 변경할 수 있다.

// object
// 1. object 선언 - Singleton
// : 오직 한개의 객체를 생성하고, 언제 어디서든 동일한 방법을 통해 접근 가능한 객체
object Cursor {
    // 초기화 블록만 사용할 수 있습니다.
    var x: Int
    var y: Int

    init {
        println("Cursor created")
        this.x = 0
        this.y = 0
    }

    // 생성자는 object 선언에서 제공할 수 없습니다.
    /*
    constructor(x: Int, y: Int) {
        this.x = x
        this.y = y
    }
    */

    fun move() {
        println("Cursor moved")
    }
}

/*
fun main() {
    println("main()")
    Cursor.move()  // Kotlin
    // Cursor.INSTANCE.move(); - Java

    val c1 = JCursor.getInstance()
    val c2 = JCursor.getInstance()

    println(c1)
    println(c2)
}
*/

// - 자바의 모든 타입은 null을 담을 수 있다. : Nullable Type
// - 코틀린의 대부분의 기능들은 자바의 것을 그대로 이용한다.

// int compare(T o1, T o2);
// class CaseInsensitiveFileComparator : Comparator<File> {
// 아래의 정책이 여러 부분에서 공유될 수 있다.
object CaseInsensitiveFileComparator : Comparator<File> {
    override fun compare(o1: File, o2: File): Int {
        return o1.path.compareTo(o2.path, ignoreCase = true)
    }
}

fun main() {
    val files = listOf(File("/Z"), File("/a"), File("/C"))

    // 1) 기존에 사용하던 방식
    // val comparator = CaseInsensitiveFileComparator()
    // var result = files.sortedWith(CaseInsensitiveFileComparator())

    // 2) 정책을 공유하는 방식
    val result = files.sortedWith(CaseInsensitiveFileComparator)
    println(result)
}








