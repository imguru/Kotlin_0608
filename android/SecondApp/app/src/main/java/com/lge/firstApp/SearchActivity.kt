package com.lge.firstApp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.lge.firstApp.model.SearchResult
import com.lge.firstApp.net.githubApi
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        button.setOnClickListener {
            githubApi.searchRepo(
                q = "Kotlin",
                page = 1,
                perPage = 5
            ).enqueue(object : Callback<SearchResult> {
                override fun onFailure(call: Call<SearchResult>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<SearchResult>,
                    response: Response<SearchResult>
                ) {
                    if (!response.isSuccessful) {
                        Log.e("XXX", "Status Code: ${response.code()}")
                        return
                    }

                    val result = response.body() ?: return
                    Log.e("XXX", "$result")
                }

            })
        }

    }
}