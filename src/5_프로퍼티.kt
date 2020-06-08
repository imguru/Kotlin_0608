// 5_프로퍼티
package ex5
// 패키지가 디렉토리로 제공될 필요가 없습니다.
// 권장사항: 디렉토리에 존재하는 것이 좋습니다.

// Getter + Setter(접근자 메소드)을 자동으로 생성하는 문법
// var: getter / setter
// val: getter
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
        // this.name = name
        // this.age = age
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