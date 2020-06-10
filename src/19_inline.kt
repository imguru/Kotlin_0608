// 19_inline

package ex19

import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock

/*
class IncThread : Thread() {
    companion object {
        var n = 0
    }

    override fun run() {
        // for (int i = 0; i < 1000000; ++i) {

        // Range: 1..1000000
        for (e in 1..1000000) {
            n++
        }
    }
}
*/


// 호출되지 않는다. 바이트 코드로 치환된다.
// Kotlin's inline
//  1) 함수가 함수를 인자로 받을 때, 성능의 저하를 막기 위해 사용 가능하다.
inline fun <T> withLock(lock: Lock, action: () -> T): T {
    lock.lock()
    try {
        return action()
    } finally {
        lock.unlock()
    }
}


class IncThread(private val lock: Lock) : Thread() {
    companion object {
        var n = 0
    }

    override fun run() {
        // for (int i = 0; i < 1000000; ++i) {

        // Range: 1..1000000
        for (e in 1..1000000) {

            withLock(lock) {
                n++
            }

        }
    }

    /*
    // 아래 코드의 문제점?
    //  : lock / unlock을 직접 호출하는 것은 위험하다.
    //    임계 영역 안에서 예외가 발생하면, unlock일 호출되지 않는다.
    //   => 예외 안정성
    override fun run() {
        // for (int i = 0; i < 1000000; ++i) {

        // Range: 1..1000000
        for (e in 1..1000000) {
            lock.lock()
            n++
            lock.unlock()
        }
    }
    */
}

fun main() {
    val lock = ReentrantLock()

    val t1 = IncThread(lock)
    val t2 = IncThread(lock)

    t1.start()
    t2.start()

    t1.join()
    t2.join()

    println("value: ${IncThread.n}")
}