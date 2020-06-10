package ex23

import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

// 범위 지정 함수
//  1. let
fun sendEmail(to: String) = println("Send email to $to")
class User {
    var email: String? = null
}


/*
fun main() {
    val user = User()

    /*
    if (user.email != null)
        sendEmail(user.email)
    */

    user.email?.let {  email ->
        sendEmail(email)
    }
}
*/

// T.() -> Unit : Extension Function
//                T를 this로 암묵적으로 전달한다.
inline fun <T> T.apply(block: T.() -> Unit): T {
    block() // block(this)
    return this
}

// 2. apply - Builder를 통해 객체를 생성할 때 사용합니다.
fun alphabet1(): String {
    val sb = StringBuilder()
    for (letter in 'A'..'Z')
        sb.append(letter)
    sb.append("\n")
    return sb.toString()
}

fun alphabet2(): String {
    return StringBuilder().apply {
        for (letter in 'A'..'Z')
            append(letter)
        append("\n")
    }.toString()
}

fun alphabet3(): String = StringBuilder().apply {
    for (letter in 'A'..'Z')
        append(letter)
    append("\n")
}.toString()


fun main() {
    println(alphabet1())
}










