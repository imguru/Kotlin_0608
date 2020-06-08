// 5_프로퍼티
package ex5

import com.lge.ex5.Person

// 패키지가 디렉토리로 제공될 필요가 없습니다.
// 권장사항: 디렉토리에 존재하는 것이 좋습니다.

// Getter + Setter(접근자 메소드)을 자동으로 생성하는 문법
// var: getter / setter
// val: getter
/*
class User(name: String, age: Int) {
    var name: String = name
        get() {
            // println("getter - $name") - 주의!
            println("getter - $field")
            return field
        }
        set(value) {
            println("setter")
            // name = value - 주의!
            field = value
        }

    val age: Int = age

    // 초기화 블록
    /*
    init {
        this.name = name
        this.age = age
    }
    */
}

fun main() {
    val user = User("Tom", 42)
    user.name = "Bob"  // user.setName("Bob")
    // user.age = 35      // user.setAge(35)

    println("${user.name}/${user.age}")
    //  user.getName()/user.getAge()
}
*/

/*
class User {
    // Property
    var name: String
    val age: Int

    constructor(name: String, age: Int) {
        this.name = name
        this.age = age
    }
}
*/

class User(var firstName: String, var lastName: String) {
    // 프로퍼티 vs 메소드
    // 1) 복잡한 코드는 메소드가 좋다.
    // 2) 시간이 오래 걸리는 작업은 메소드가 좋다.
    // 3) Getter에서 객체 내부의 값을 변경하면 안된다.
    // 4) 다른 타입으로 변경하는 작업은 메소드를 사용해야 한다.
    //    toString() -> String
    //    toInt()    -> Int
    // 5) 객체를 복제하는 작업은 메소드를 사용해야 한다.

    // Backing field가 없는 프로퍼티
    //  : field 키워드를 사용할 수 없습니다.
    //    초기화할 수 없습니다.
    var fullName: String
        get() {
            return "$firstName $lastName"
        }

        set(value) {
            val arr = value.split(" ")
            firstName = arr[0]
            lastName = arr[1]
        }
}

fun main() {
    val person = Person("Tom")
    // println(person.getName())
    println(person.name)

    person.name = "Bob"
    person.fullName = "Chansik Yun"
    person.isMale = true
    // person.setName("Bob")

    val user = User("Gildong", "Hong")
    println(user.firstName)
    println(user.lastName)

    println(user.fullName)
}
















