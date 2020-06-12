package com.lge.firstApp.net

import com.lge.firstApp.model.SearchResult
import com.lge.firstApp.model.User
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

// REST API를 연동할 때 반복적으로 발생하는 코드가 있습니다.
// 1. HTTP 클라이언트를 이용해서 요청을 작성하고,
// 2. 받은 응답을 객체로 직렬화하는 과정이 반복 됩니다.
// => 위의 코드를 어노테이션을 통해 코드를 자동으로 생성하는 라이브러리 - Retrofit
//  어노테이션 코드 생성 기술
//  1) Glide    - 어노테이션 프로세서
//  2) Retrofit - 런타임에 리플렉션을 통해 생성

// Retrofit 적용 방법
// 1. Inteface 를 정의한다.
interface GithubApi {
    @GET("users/{name}")
    fun getUser(@Path("name") name: String): Call<User>
    // Response.body: JSON -> User
    //                  Converter(Gson)

    // search/repositories?q=Kotlin&page=1&per_page=5
    @GET("search/repositories")
    fun searchRepo(
        @Query("q") q: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Call<SearchResult>

}

// 2. GithubApi 인터페이스를 기반으로 코드를 생성합니다.
// 2-1
private val retrofit: Retrofit = Retrofit.Builder().apply {
    baseUrl("https://api.github.com/")
    client(OkHttpClient())
    addConverterFactory(GsonConverterFactory.create())

}.build()

val githubApi: GithubApi = retrofit.create(GithubApi::class.java)











