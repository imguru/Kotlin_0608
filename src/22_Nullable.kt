package ex22

// Nullable
//  : Null을 안전하게 다루는 기술
//  = Optional : Swift

/*
inline fun <T, R> T.let(block: (T) -> R): R {
    return block(this)
}
*/

/*
var name: String? = "hello"
fun main() {
    // 1. 일반 타입에 null을 사용하는 것은 허용되지 않습니다.
    // val name: String = null
    // var name: String? = "hello"

    // 2. Nullable 타입은 반드시 null 체크가 필요합니다.
    //  : Smart Cast는 전역 프로퍼티 또는 객체의 프로퍼티가 var인 경우 제대로 동작하지 않습니다.

    /*
    val name = name // 해결 방법 1.
    if (name != null) {
        val length = name.length  // Smart Cast: String? -> String
        println("$name - $length")
    }
    */

    // 해결방법 2. let 함수를 이용한다. + Safe Call(?.)
    name?.let { name ->
        val length = name.length
        println("$name - $length")
    }

    // 해결방법 3. 강제 접근(!!)
    //  : Null일 경우 예외가 발생합니다. - 추천하지 않습니다.
    val length = name!!.length
    println("$name - $length")
}
*/

// 안전한 Null 참조 연산(Safe Call Access Operator - ?.)
class Person(name: String, val address: Address?)
class Address(name: String, postcode: String, val city: City?)
class City(name: String, val country: Country?)
class Country(val name: String)

// 안전한 널 참조 연산이 존재하지 않는다면
/*
fun getCountryName(person: Person?): String? {
    var countryName: String? = null
    if (person != null) {
        val address = person.address
        if (address != null) {
            val city = address.city
            if (city != null) {
                val country = city.country
                if (country != null) {
                    countryName = country.name
                }
            }
        }
    }
    return countryName
}
*/

/*
fun getCountryName(person: Person?): String? {
    var countryName: String? = null
    if (person == null) return null // 빠른 탈출

    val address = person.address
    if (address == null) return null

    val city = address.city
    if (city == null) return return null

    val country = city.country
    if (country == null) return null
    countryName = country.name
    return countryName
}
*/

fun getCountryName(person: Person?): String? {
    if (person == null) return null // 빠른 탈출

    val address = person.address ?: return null
    val city = address.city ?: return null
    val country = city.country ?: return null

    return country.name
}

/*
fun getCountryName(person: Person?): String? {
    return person?.address?.city?.country?.name
}
*/

fun main() {
    // null이면 기본값으로 처리하고 싶다. - ?: (Elvis Operator)
    val countryName = getCountryName(null) ?: "Unknown"
    println(countryName)
}







