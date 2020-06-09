package ex11_2

import kotlin.reflect.KProperty

// 위임
// 1. 클래스 위임
// 2. 프로퍼티 위임
//   => getter / setter의 동작을 다른 객체에게 위임하겠다.


// 값이 변경되는 것을 감지하는 기능이 필요하다.
interface Observer<T> {
    fun onValueChanged(old: T, new: T) {
        println("ValueChanged: $old -> $new")
    }
}

class Observable<T>(var value: T, private val observer: Observer<T>? = null) {
    operator fun getValue(ref: Any, property: KProperty<*>): T {
        return value
    }

    operator fun setValue(ref: Any, property: KProperty<*>, new: T) {
        val old = value
        value = new
        observer?.onValueChanged(old, new)
    }
}

class User(name: String) {
    // getter / setter의 동작에 대해서 연산자 함수를 통해 약속되어 있다. - C++ Style
    var age: Int by Observable(0, object : Observer<Int> {
    })

    var name: String by Observable(name, object : Observer<String> {
        override fun onValueChanged(old: String, new: String) {
            println("ValueChanged: $old -> $new")
        }
    })

}

fun main() {
    val user = User("Tom")
    user.age = 10
    user.age = 20
    println(user.name)

    user.name = "Bob"
    println(user.name)
}

/*
class SampleDelegate {
    operator fun getValue(thisRef: Any, property: KProperty<*>): String {
        return "SampleDelegate - getValue($thisRef / $property)"
    }

    operator fun setValue(thisRef: Any, property: KProperty<*>, value: String) {
        println("SampleDelegate - setValue($value)")
    }
}

class User {
    // getter / setter의 동작에 대해서 연산자 함수를 통해 약속되어 있다. - C++ Style
    var name: String by SampleDelegate()
}

fun main() {
    val user = User()
    println(user.name)  // Getter

    user.name = "Bob"
}
*/
