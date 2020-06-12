package com.lge.firstApp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_search.*


class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)


        // Recycler View 사용하는 방법
        //  : ListView -> RecyclerView
        // 1) View holder Pattern을 ListView는 직접 구현해야 합니다.
        //     searchRecyclerView.layoutManager = LinearLayoutManager(this)
        // 2) GridView 같은 View의 표현 방식이 불가능하다.
        //   => Layout Manager
        //     - 코드로 직접 작성하는 방법
        //     - XML을 통해 작성하는 방법
        //      app:layoutManager="androidx.recyclerview.widget.LinearLayoutManager"





    }
}


/*
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
                    // Log.e("XXX", "$result")
                }

            })
        }

    }
}
*/