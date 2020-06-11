package com.lge.firstApp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            okhttp3Version2()
        }
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

                runOnUiThread {
                    Toast.makeText(this@MainActivity, "OK - $json", Toast.LENGTH_SHORT).show()
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


// Server 연동
//    - Github API v3 - REST API(JSON)
//    - Github API v4 - GraphQL  X
//   : https://github.com/JakeWharton
//    => https://api.github.com/users/JakeWharton
/*
{
  "login": "JakeWharton",
  "id": 66577,
  "node_id": "MDQ6VXNlcjY2NTc3",
  "avatar_url": "https://avatars0.githubusercontent.com/u/66577?v=4",
  "gravatar_id": "",
  "url": "https://api.github.com/users/JakeWharton",
  "html_url": "https://github.com/JakeWharton",
  "followers_url": "https://api.github.com/users/JakeWharton/followers",
  "following_url": "https://api.github.com/users/JakeWharton/following{/other_user}",
  "gists_url": "https://api.github.com/users/JakeWharton/gists{/gist_id}",
  "starred_url": "https://api.github.com/users/JakeWharton/starred{/owner}{/repo}",
  "subscriptions_url": "https://api.github.com/users/JakeWharton/subscriptions",
  "organizations_url": "https://api.github.com/users/JakeWharton/orgs",
  "repos_url": "https://api.github.com/users/JakeWharton/repos",
  "events_url": "https://api.github.com/users/JakeWharton/events{/privacy}",
  "received_events_url": "https://api.github.com/users/JakeWharton/received_events",
  "type": "User",
  "site_admin": false,
  "name": "Jake Wharton",
  "company": "Square",
  "blog": "https://jakewharton.com",
  "location": "Pittsburgh, PA, USA",
  "email": null,
  "hireable": null,
  "bio": null,
  "twitter_username": null,
  "public_repos": 104,
  "public_gists": 54,
  "followers": 57822,
  "following": 12,
  "created_at": "2009-03-24T16:09:53Z",
  "updated_at": "2020-05-28T00:07:20Z"
}
*/