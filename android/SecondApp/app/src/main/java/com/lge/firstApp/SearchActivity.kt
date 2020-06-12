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
import com.lge.firstApp.net.githubApi
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.plusAssign
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.item_repo.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

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
                        this@SearchActivity,
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
                    adapter.items = result.items
                }

            })


            // githubApi.searchRepo()


            // adapter.items에 내용이 변경되면 자동으로 호출하고 싶다.
            // adapter.notifyDataSetChanged()

        }


        // Recycler View 사용하는 방법
        //  : ListView -> RecyclerView
        //   1) View holder Pattern을 ListView는 직접 구현해야 합니다.
        //     searchRecyclerView.layoutManager = LinearLayoutManager(this)
        //   2) GridView 같은 View의 표현 방식이 불가능하다.
        //   => Layout Manager
        //     - 코드로 직접 작성하는 방법
        //     - XML을 통해 작성하는 방법
        //      app:layoutManager="androidx.recyclerview.widget.LinearLayoutManager"

        // 데이터를 RecyclerView에 표현하기 위해서는 'Adapter'가 필요합니다.
    }
}


// View holder Pattern: 화면에 보이는 만큼의 View만 생성하고, View의 내용을 바꾸면서 재사용하는 기법

// View를 담는 객체
class ViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_repo, parent, false)
) {
    var disposeBag = CompositeDisposable()
}

class SearchAdapter : RecyclerView.Adapter<ViewHolder>() {
    var items: List<Repo> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = items.count()

    // 재사용 가능한 View가 없을 경우, 생성하는 함수
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(parent)


    // 재사용하는 View에 대해서 내용을 변경하는 함수
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = items[position]

        /*
        holder.itemView.repoTextView.text = model.fullName
        holder.itemView.descTextView.text = model.description
        holder.itemView.nameTextView.text = model.owner.login

        GlideApp.with(holder.itemView)
            .load(model.owner.avatarUrl)
            .avatar()
            .into(holder.itemView.avatarImageView)
        */

        holder.disposeBag.dispose()
        holder.disposeBag = CompositeDisposable()
        with(holder.itemView) {
            repoTextView.text = model.fullName
            descTextView.text = model.description
            nameTextView.text = model.owner.login

            githubApi.rxGetUser(model.owner.login)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { user ->
                    companyTextView.text = user.company
                    Log.e("XXX", "$user")
                }.addTo(holder.disposeBag)

            GlideApp.with(this)
                .load(model.owner.avatarUrl)
                .avatar()
                .into(avatarImageView)
        }
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