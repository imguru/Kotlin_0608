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


class SearchActivity2 : AppCompatActivity() {
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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        main()

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