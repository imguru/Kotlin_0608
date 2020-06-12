package com.lge.firstApp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.lge.firstApp.net.githubApi
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.kotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_search.*


class SearchActivity3 : AppCompatActivity() {

    private val disposeBag = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)


        val adapter = SearchAdapter()
        searchRecyclerView.adapter = adapter

        searchButton.setOnClickListener {

            val observable = githubApi.rxGetUser("JakeWharton")
            // observable.subscribe() // RxJava

            /*
            observable.subscribeBy(onNext = { user ->
                Log.e("XXX", "onNext - $user")

            }, onError = {
                Log.e("XXX", "onError - $it")
            }, onComplete = {
                Log.e("XXX", "onComplete")
            }).addTo(disposeBag)
            */

            disposeBag += observable
                .observeOn(AndroidSchedulers.mainThread())          // Observer의 동작이 수행되는 스레드를 지정할 수 있다.
                .subscribeBy(onNext = { user ->
                    Log.e("XXX", "onNext - $user")

                    Toast.makeText(
                        this@SearchActivity3,
                        "user - ${user.name} / ${user.company}",
                        Toast.LENGTH_SHORT
                    ).show()


                }, onError = {

                    Log.e("XXX", "onError - $it")
                }, onComplete = {

                    Log.e("XXX", "onComplete")
                })


        }
    }

    override fun onDestroy() {
        disposeBag.dispose()

        super.onDestroy()
    }
}