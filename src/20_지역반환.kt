package ex20

data class Person(val name: String)

/*
fun lookForAlice(people: List<Person>) {
    for (person in people) {
        if (person.name == "Alice") {
            println("Found!")
            return  // continue
        }
    }

    println("Not Found!")
}
*/

// 1. 람다 표현식에서의 return은 비지역반환을 수행한다.
//    람다 표현식을 감싸고 있는 함수의 반환을 수행한다.
//    inline 함수는 호출되지 않고, 치환되기 때문에, forEach2는 lookForAlice 함수 안에서 호출 없이 수행된다.
//    중요: inline 함수가 아니면 비지역 반환을 사용할 수 없다.

inline fun <T> Iterable<T>.forEach2(action: (T) -> Unit): Unit {
    for (element in this) action(element)
}

// forEach
fun lookForAlice(people: List<Person>) {
    people.forEach2 { person ->
        if (person.name == "Alice") {
            println("Found!")
            return  // forEach에 대한 return이 아니라 lookForAlice에 대한 반환입니다.
        }
    }

    println("Not Found!")
}
/*
    for (person in people) {
        if (person.name == "Alice") {
            println("Found!")
            continue
        }
    }
*/
// 람다 표현식에서는 지역 반환을 또한 제공하고 있습니다.
fun lookForAlice2(people: List<Person>) {
    people.forEach2 { person ->
        if (person.name == "Alice") {
            println("Found!")
            return@forEach2
        }
    }

    println("Not Found!")
}

// 람다 표현식이 아닌 익명 함수 버전입니다.
//  => 익명 함수에서는 return이 지역 반환을 수행합니다.
//     익명 함수에서도 비지역 반환을 수행할 수 있습니다.

fun lookForAlice3(people: List<Person>) {
    people.forEach2(fun(person: Person) {
        if (person.name == "Alice") {
            println("Found!")
            // return  // 지역 반환
            return@lookForAlice3
        }
    })

    println("Not Found!")
}


fun main() {
    val people = listOf(Person("Tom"), Person("Alice"))

    lookForAlice3(people)

    // 지역 반환할 때 이름이 존재하지 않는 경우, 이름을 지정할 수 있다.
    val f = hello@{
        println("f")
        return@hello
    }
}






