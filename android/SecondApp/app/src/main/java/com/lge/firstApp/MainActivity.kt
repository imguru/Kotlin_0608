package com.lge.firstApp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException
import java.util.*

// gson.fromJson(json, User::class.java)

inline fun <reified T> Gson.fromJson(json: String): T = fromJson(json, T::class.java)


inline fun Call.enqueue(
    crossinline onResponse: (response: Response) -> Unit,
    crossinline onFailure: (e: IOException) -> Unit
) {
    enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            onFailure(e)
        }

        override fun onResponse(call: Call, response: Response) {
            onResponse(response)
        }
    })
}


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            okhttp3Version3()
        }
    }

    private fun okhttp3Version3() {
        val client = OkHttpClient()
        val request = Request.Builder().apply {
            url("https://api.github.com/users/JakeWharton")
        }.build()
        val call = client.newCall(request)

        // Kotlin에서 여러개의 함수를 인자로 받는 경우, 파라미터 지정 호출이 좋습니다.
        call.enqueue(onResponse = { response ->
            if (response.isSuccessful.not()) {
                return@enqueue
            }

            val body = response.body ?: return@enqueue
            val json = body.string()

            val gson = Gson()
            val user = gson.fromJson<User>(json)

            runOnUiThread {
                Toast.makeText(this, "OK - $user", Toast.LENGTH_SHORT).show()
            }

        }, onFailure = {

        })

    }


    // 비동기 - 1. 결국 별도의 스레드를 통해 처리한다.
    //        2. 스레드를 매번 새롭게 생성하는 것이 아니라, 스레드 풀을 통해 처리한다.
    //        3. 스레드풀에게 작업을 전달하는 큐에 작업을 집어 넣으면 된다.
    //        4. 사건의 완료 시점을 알 수 없다. - 콜백(Callback)
    //                                     : 비동기에서 사건의 성공 실패 여부를 알려주는 역활
    private fun okhttp3Version2() {
        val client = OkHttpClient()
        val request = Request.Builder().apply {
            url("https://api.github.com/users/JakeWharton")
        }.build()
        val call = client.newCall(request)

        call.enqueue(object : Callback {
            // 서버에 도달하지 못함.
            override fun onFailure(call: Call, e: IOException) {
            }

            override fun onResponse(call: Call, response: Response) {
                /*
                if (!response.isSuccessful) {
                    return
                }
                */
                if (response.isSuccessful.not()) {
                    return
                }

                val body = response.body ?: return
                val json = body.string()

                // 1. User 클래스 작성
                // 2. Gson 객체를 생성
                val gson = Gson()
                // val user = gson.fromJson(json, User::class.java)
                val user = gson.fromJson<User>(json)


                runOnUiThread {
                    Toast.makeText(this@MainActivity, "OK - $user", Toast.LENGTH_SHORT).show()
                }

            }
        })
    }


    private fun okhttp3Version1() {
        // 1. OkHttpClient 객체 생성
        val client = OkHttpClient()

        // 2. Request - GET
        val request = Request.Builder().apply {
            url("https://api.github.com/users/JakeWharton")
        }.build()

        // 3. Call 객체 생성
        //  : 동기 또는 비동기
        //  => 사용자가 동기 호출 또는 비동기 호출을 결정할 수 있다.
        val call = client.newCall(request)

        // 4. 동기
        /*
           1. class MyThread extends Thread {}
           2. new Thread(new Runnable() {});
           //            'SAM 지원이 사용 가능하다'
        */

        Thread {
            try {
                // call.execute 실행 할때, 인터넷이 연결이 되어 있지 않거나, 서버에 도달할 수 없을 경우
                // 예외가 발생한다.
                val response = call.execute()
                // NetworkOnMainThreadException
                //  : Android's Main Thread: UI를 업데이트 하는 역활
                //    => 네트워크 요청 등의 시간이 오래 걸릴 수 있는 작업에 대해서는 Main Thread에서 수행하면 안된다.

                // Permission denied (missing INTERNET permission?)

                // 5. 서버의 응답 코드(statusCode)를 통해 요청의 성공 실패를 판단해야 한다.
                // 200 ~ 299 = OK
                // 400 ~ 499 = Client Error
                // 500 ~ 599 = Server Error

                // if (response.code in 200..299) {

                // val json = body?.string()

                if (response.isSuccessful) {
                    val json = response.body?.string()
                    if (json != null) {

                        runOnUiThread {
                            Toast.makeText(this, "OK - $json", Toast.LENGTH_SHORT).show()
                            // Can't toast on a thread that has not called Looper.prepare()
                            //   : 메인 스레드가 아닌 다른 스레드에서 UI에 대한 업데이트를 수행하였다.
                        }

                    }

                    /*
                response.body?.let { body ->
                    val json = body.string()  // toString()

                    // Log.e("XXX", "Response: $json")
                    Toast.makeText(this, "OK - $json", Toast.LENGTH_SHORT).show()
                }
                */
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }.start()
    }
}

// HTTP API
//  => OKHttp
//   app - build.gradle
//    implementation 'com.squareup.okhttp3:okhttp:4.7.2'

//                직렬화(Serialization)
//  => JSON -> Object -> JSON
//  역직렬화(Deserialization)
//  : Gson(https://github.com/google/gson)
//     implementation 'com.google.code.gson:gson:2.8.6'

// Server 연동
//    - Github API v3 - REST API(JSON)
//    - Github API v4 - GraphQL  X
//   : https://github.com/JakeWharton
//    => https://api.github.com/users/JakeWharton

/*
{
  "login": "JakeWharton",
  "avatar_url": "https://avatars0.githubusercontent.com/u/66577?v=4",
  "name": "Jake Wharton",
  "company": "Square",
  "location": "Pittsburgh, PA, USA",
  "public_repos": 104,
  "created_at": "2009-03-24T16:09:53Z",
  "updated_at": "2020-05-28T00:07:20Z"
}
*/


// 1. @field:SerializedName("avatar_url") => JSON의 키값을 명시할 수 있다.
// 2. proguard에서 제외해야 한다.
data class User(
    val login: String,
    val name: String,
    val company: String,
    val location: String,
    @field:SerializedName("avatar_url") val avatarUrl: String,
    @field:SerializedName("public_repos") val publicRepos: Int,
    @field:SerializedName("created_at") val createdAt: Date,
    @field:SerializedName("updated_at") val updatedAt: Date
)

/*
interface OnClickListener {
    fun onClick(sender: Button)
}

class Button {
    var listener: OnClickListener? = null

    fun click() {
        listener?.onClick(this)
    }
}

class Dialog : OnClickListener {
    fun close() {}
    fun open() {}

    override fun onClick(sender: Button) {
        // ....
    }
}

fun main() {
    val openButton = Button()
    val closeButton = Button()
    val dialog = Dialog()

    openButton.listener = dialog
    closeButton.listener = dialog
}
*/











