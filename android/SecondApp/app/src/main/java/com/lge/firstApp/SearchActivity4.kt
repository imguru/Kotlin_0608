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
import com.jakewharton.rxbinding4.view.clicks
import com.lge.firstApp.model.Repo
import com.lge.firstApp.model.SearchResult
import com.lge.firstApp.model.User
import com.lge.firstApp.net.githubApi
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.Observables
import io.reactivex.rxjava3.kotlin.flatMapIterable
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.kotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.item_repo.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.TimeUnit

class SearchActivity4 : AppCompatActivity() {
    val disposeBag = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        // main()

        val adapter = SearchAdapter()
        searchRecyclerView.adapter = adapter


        // [ "tom",                "bob" ]
        //     |                     |
        //  Observables<User>   Observables<User>


        /*
        Observables.combineLatest(
            githubApi.rxGetUser("Tom"),
            githubApi.rxGetUser("Bob")
        ).subscribeBy(onNext = {
            val (first, second) = it
            Log.e("XXX", "$first $second")
        })
        */

        disposeBag += searchButton.clicks()
            .throttleFirst(1, TimeUnit.SECONDS)
            .filter {
                queryEditText.text.isBlank().not()
            }
            .map {
                queryEditText.text.trim().toString()   // Unit -> String
            }
            .flatMap { keyword ->
                githubApi.rxSearchRepo(keyword)
            }
            .map { result ->
                result.items
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(onNext = { result ->
                adapter.items = result
            }, onError = {
                Log.e("XXX", "Error - ${it.localizedMessage}")
            })


    }

    override fun onDestroy() {
        disposeBag.dispose()
        super.onDestroy()
    }
}