package com.lge.firstApp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            githubApi.getUser("JakeWharton").enqueue(object : Callback<User> {
                override fun onFailure(call: Call<User>, t: Throwable) {
                }

                // UI Thread에서 수행되는 것을 보장합니다.
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if (!response.isSuccessful)
                        return

                    val user = response.body() ?: return

                    nameTextView.text = user.name
                    Glide.with(this@MainActivity2)
                        .load(user.avatarUrl)
                        .into(avatarImageView)
                }
            })
        }
    }
}

// Retrofit
// 1. API Interface를 설계한다.
interface GithubApi {
    @GET("users/{name}")
    fun getUser(@Path("name") name: String): Call<User>



}

// 2. Retrofit을 통해 코드를 생성한다.
val githubApi: GithubApi = Retrofit.Builder().apply {

    // 서버의 주소를 설정한다.
    baseUrl("https://api.github.com")
    client(OkHttpClient())
    addConverterFactory(GsonConverterFactory.create())


}.build().create(GithubApi::class.java)







