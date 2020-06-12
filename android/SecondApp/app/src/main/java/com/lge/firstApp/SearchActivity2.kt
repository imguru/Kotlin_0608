package com.lge.firstApp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.lge.firstApp.model.Repo
import com.lge.firstApp.model.SearchResult
import com.lge.firstApp.model.User
import com.lge.firstApp.net.githubApi
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.item_repo.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


// Rx - 비동기의 흐름을 제어할 수 있다.

// 동기
// a()
// b()
// c()
// print

// 비동기 흐름을 제어하기 어렵다.
// a {
//   b {
//     c {
//          print
//     }
//   }
// }
//  => Callback hell

// Android에서 비동기의 흐름을 제어할 때 가장 많이 사용하는 방법
//  => Rx(Reactive eXtension)
//    : Java, JS, Swift ...

// Collection
//       List    - forEach, map, filter

// Iterable<Int>           -            Iterator<Int>


/*
class Node(var n: Int, var next: Node? = null)

class SList : Iterable<Int> {
    var head: Node? = null

    fun pushFront(n: Int) {
        head = Node(n, head)
    }

    fun front(): Int? = head?.n

    override fun iterator(): Iterator<Int> {
        return SListIterator(head)
    }
}

class SListIterator(var current: Node? = null) : Iterator<Int> {
    override fun hasNext(): Boolean = current != null

    override fun next(): Int {
        val ret = current!!.n
        current = current?.next

        return ret
    }
}
*/

// Iterable     - Iterator - pull
// Observable   - Observer - push


// Rx의 5가지 요소
// 1. Observable
//   : 이벤트를 만들어내는 주체로 이벤트 스트림을 통해 이벤트를 내보냅니다.

// 2. Observer
//   : Observable에서 만들어진 이벤트에 반응하는 객체. 이벤트를 받았을 때 수행할 작업을 정의합니다.
//    "Observer가 Observable을 구독(Subscribe)한다" 라고 표현합니다.

// 3. Operator(연산자)
//   : 연산자는 이벤트 스트림을 통해 전달되는 이벤트를 대상으로 연산을 수행합니다.
//    - map, flatMap, filter, zip, combine ...

// 4. Scheduler
//   : 작업을 수행할 스레드를 지정할 수 있습니다.
//    - Main, IO, Worker, 새로운 스레드

// 5. Disposable
//   : Obsever가 Observable에 대해 구독할 때 생성되는 객체로, Observable에서 만드는 이벤트 스트림과
//     필요한 리소스를 관리합니다.
//   => 사용하지 않을 때 해지해야 한다.

// 아래 라이브러리에 대한 의존성 추가가 필요합니다.
// RxJava
//  - implementation "io.reactivex.rxjava3:rxjava:3.0.4"
// RxKotlin
//  - implementation "io.reactivex.rxjava3:rxkotlin:3.x.y"
// RxAndroid
//  - implementation 'io.reactivex.rxjava3:rxandroid:3.0.0'

// Retrofit +
//  - implementation 'com.squareup.retrofit2:adapter-rxjava2:latest.version'



class SearchActivity2 : AppCompatActivity() {
    /*
    fun main() {
        val list = SList()
        list.pushFront(10)
        list.pushFront(20)
        list.pushFront(30)

        Log.e("XXX", "front - ${list.front()}")

        /*
        val iterator = list.iterator()
        while (iterator.hasNext()) {
            Log.e("XXX", "${iterator.next()}")
        }
        */
        // Collection - Iterator / Iterable
        for (e in list) {
            Log.e("XXX", "$e")
        }

        list.forEach { e ->
            Log.e("XXX", "$e")
        }

        Log.e("XXX", "${list.filter { e ->
            e > 10
        }}")
    }
    */


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        // main()

        val adapter = SearchAdapter()
        searchRecyclerView.adapter = adapter

        searchButton.setOnClickListener {
            val query = queryEditText.text.toString().trim()
            if (query.isBlank()) {
                return@setOnClickListener
            }

            githubApi.searchRepo(q = query, perPage = 30).enqueue(object : Callback<SearchResult> {
                override fun onFailure(call: Call<SearchResult>, t: Throwable) {
                    Toast.makeText(
                        this@SearchActivity2,
                        "Error - ${t.localizedMessage}",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onResponse(
                    call: Call<SearchResult>,
                    response: Response<SearchResult>
                ) {
                    if (!response.isSuccessful)
                        return

                    val result = response.body() ?: return

                    result.items.firstOrNull()?.let { repo ->
                        githubApi.getUser(repo.owner.login).enqueue(object : Callback<User> {
                            override fun onFailure(call: Call<User>, t: Throwable) {

                            }

                            override fun onResponse(call: Call<User>, response: Response<User>) {
                                val user = response.body() ?: return
                                repo.company = user.company

                                adapter.items = result.items

                            }
                        })
                    }

                    // adapter.items = result.items

                }

            })
        }

    }
}