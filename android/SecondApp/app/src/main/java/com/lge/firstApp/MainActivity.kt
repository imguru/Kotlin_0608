package com.lge.firstApp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import okhttp3.Request

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            okhttp3Version1()
        }
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
        val response = call.execute()

        // 5. 서버의 응답 코드(statusCode)를 통해 요청의 성공 실패를 판단해야 한다.
        // 200 ~ 299 = OK
        // 400 ~ 499 = Client Error
        // 500 ~ 599 = Server Error

        // if (response.code in 200..299) {
        if (response.isSuccessful) {
            val body = response.body
            val json = body.toString()
            Log.e("XXX", "Response: $json")
        }
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