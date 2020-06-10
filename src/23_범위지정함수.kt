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
//inline fun <T> T.apply(block: T.() -> Unit): T {
//    block() // block(this)
//    return this
//}

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

fun alphabet4(): String = buildString {
    for (letter in 'A'..'Z')
        append(letter)
    append("\n")
}

/*
fun main() {
    println(alphabet1())
    println(alphabet2())
    println(alphabet3())
    println(alphabet4())
}
*/

// 3. with
class TextView(var text: String = "")
class Button(var text: String = "")

class View(val textView: TextView, val button: Button)
class ViewHolder(val view: View)

/*
inline fun <T, R> with(receiver: T, block: T.() -> R): R {
    return receiver.block()
}
*/

fun main() {
    val viewHolder = ViewHolder(View(TextView(), Button()))

    viewHolder.view.button.text = "Button"
    viewHolder.view.textView.text = "TextView"

    // with
    with(viewHolder.view) {
        button.text = "Button"
        textView.text = "TextView"
    }
}











