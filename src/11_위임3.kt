package ex11_3

import java.util.concurrent.TimeUnit
import kotlin.properties.Delegates


data class Point(val x: Int, val y: Int) {
    init {
        println("오래 걸리는 작업입니다.")
        TimeUnit.SECONDS.sleep(3)
    }
}

// 1. lazy
//  : val 프로퍼티에 대해서 지연 초기화를 구현할 수 있다.
//    생성이 스레드 안전하게 수행함을 보장한다.

open class AppCompatActivity {
    open fun onCreate() {}
}

class MainActivity : AppCompatActivity() {
    // val button = findViewById(R.id.button)
    // val button by lazy {
    //   findViewById(R.id.button)
    // }

    override fun onCreate() {
        super.onCreate()
        // setContentView(R.layout.activity_main)

        // button.setOnClickListener(...)
    }
}


/*
class User {
    // val name: Point = Point(10, 20)   // 처음 접근되는 시점에 생성하고 싶다.
                                         // - 지연 초기화 기법
    /*
    var name: Point? = null
        get() {
            if (field == null)
                field = Point(10, 20)
            return field
        }
    */
    // lazy를 통해 실행되는 블록은 스레드 안전하다.
    val name: Point by lazy {
        Point(10, 20)
    }
}

fun main() {
    val user = User()
    println("User 객체 생성됨")
    println(user.name)
}
*/

/*
class Point(val x: Int, val y: Int)
class User {
    var name: String = ""
    // var point: Point? = null
    // vs
    // lateinit var point: Point - X

    // 1. var 에서만 사용할 수 있다.
    // 2. getter / setter를 재정의하는 것이 불가능하다.
    // 3. Primitive Type(Int, Double, Long, Float...)에서는 사용할 수 없다.
}

fun main() {
    val user = User()
    println(user.name) // UninitializedPropertyAccessException

    user.name = "Tom"
    println(user.name)
}
*/

// 2. KVO(Key-Value Observation) - Apple에서 유래
//  : 프로퍼티의 값의 변경을 통보받는 기술
//   -> Delegates.observable
class TextView {
    // _ : 사용하지 변수의 이름을 지정하는 방법.
    var text: String by Delegates.observable("Tom") { _, old, new ->
        println("Value Changed: $old -> $new")
        update()
    }

    private fun update() {
        println("$text updated")
    }
}

/*
fun main() {
    val textView = TextView()
    textView.text = "Bob"

    textView.text = "Tom"
}
*/

// 3. KVC(Key-Value Coding)
//   : map or JSON 을 통해서 객체의 프로퍼티를 초기화하는 기술
// JSON: Map<String, Any>

// Map 객체를 프로퍼티의 위임 객체로 사용하는 것이 가능하다.
class Person(json: Map<String, Any>) {
    val name: String by json
    val age: Int by json
//    val address: String by json

//    init {
//        name = json["name"] as String
//        age = json["age"] as Int
//        address = json["address"] as String
//    }

    override fun toString(): String {
        return "Person(name='$name', age=$age)"
    }
}

/*
fun main() {
    val json: Map<String, Any> = mapOf(
        "name" to "Tom",
        "age" to 42,
        "address" to "Suwon"
    )

    val person = Person(json)
    println(person)
}
*/

// 4. vetoable: validation 조건에 부합되지 않으면, 값이 변경되지 않도록 한다.
class Address {
    // name은 반드시 5글자 이상이어야 한다.
    var name: String by Delegates.vetoable("Suwon") { _, old, new ->
        // Validation 조건 - true or false가 결과가 되어야 한다.
        isValidAddressName(new)
    }

    private fun isValidAddressName(name: String): Boolean {
        return name.length >= 5
    }
}

fun main() {
    val address = Address()
    address.name = "A"
    address.name = "Seoul"
    println(address.name)
}






