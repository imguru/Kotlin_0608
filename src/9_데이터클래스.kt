package ex9

// Data Class
//  => VO(Value Object), DAO(Database Access Object), DTO(Data Transfer Object)
// 1. toString
// 2. equals
// 3. hashCode
// 4. copy 라는 기능을 제공합니다.
//   => 객체를 복제하는 기능
// 5. 구조 분해 선언 문법

// Kotlin 에서는 clone을 제공하지 않습니다.
data class User(val name: String, val age: Int)

class Person(val name: String, val age: Int) {
    operator fun component1(): String {
        return name
    }
    operator fun component2(): Int {
        return age
    }
}

fun main() {
    // Kotlin - operator overloading(연산자 오버로딩)
    //  => 특정한 구문을 사용하였을 때, 약속된 연산자 함수가 호출되는 형식
    val (n, s) = Person("Tom", 42)
    println("$n,$s")


    val user1 = User("Tom", 42)
    println(user1)

    // val user2 = User("Tom", 42)
    // val user2 = user1.copy()
    val user2 = user1.copy(age = 30)

    if (user1 != user2) {
        println("Not Same!")
    }

    // List<User> list = new ArrayList<>();
    // list.add(user1);
    // list.add(user2);

    // 인자의 타입을 통해 T가 암묵적으로 추론된다.
    val list = listOf(user1, user2)

    // 구조 분해 선언 문법
    val (name, age) = user1
    println("$name,$age")

    for ((name, age) in list) {
        println("name=${name}/age=${age}")
    }
    /*
    for (e in list) {
        println("name=${e.name}/age=${e.age}")
    }
    */


//    println(user1)
//    println(user2)
}