package com.lge.firstApp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.jakewharton.rxbinding4.view.clicks
import com.lge.firstApp.model.Repo
import com.lge.firstApp.net.githubApi
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.kotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_search.*
import java.util.concurrent.TimeUnit

// Network 요청 뿐 아니라, 안드로이드 View의 이벤트를 Observable를 통해 처리할 수 있다. - RxBinding(View + Observable)
// implementation 'com.jakewharton.rxbinding4:rxbinding:4.0.0'
// implementation 'com.jakewharton.rxbinding4:rxbinding-core:4.0.0'
// implementation 'com.jakewharton.rxbinding4:rxbinding-material:4.0.0'
// implementation 'com.jakewharton.rxbinding4:rxbinding-appcompat:4.0.0'


// flatMap
//    [ 1, 2, 3 ]
//         |           map:  [ [1], [1, 2], [1, 2, 3] ]  / flatMap: [ 1, 1, 2, 1, 2, 3 ]
//    1 - [ 1 ]
//    2 - [ 1, 2 ]
//    3 - [ 1, 2, 3 ]




class SearchActivity3 : AppCompatActivity() {

    private val disposeBag = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)


        val adapter = SearchAdapter()
        searchRecyclerView.adapter = adapter

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
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(onNext = { result ->
                adapter.items = result.items
            }, onError = {
                Log.e("XXX", "Error - ${it.localizedMessage}")
            })



        /*
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

            // .filter, .map, .throttle
            disposeBag += observable
                .throttleFirst(3, TimeUnit.SECONDS)
                .filter { user ->
                    user.company.length > 5
                }
                .map { user ->
                    Repo.Owner(
                        login = user.login,
                        type = "User",
                        avatarUrl = user.avatarUrl
                    )
                }
                .observeOn(AndroidSchedulers.mainThread())          // Observer의 동작이 수행되는 스레드를 지정할 수 있다.
                .subscribeBy(onNext = { user ->
                    Log.e("XXX", "onNext - $user")

                    Toast.makeText(
                        this@SearchActivity3,
                        "user - ${user}",
                        Toast.LENGTH_SHORT
                    ).show()


                }, onError = {

                    Log.e("XXX", "onError - $it")
                }, onComplete = {

                    Log.e("XXX", "onComplete")
                })
        }
        */
    }

    override fun onDestroy() {
        disposeBag.dispose()

        super.onDestroy()
    }
}