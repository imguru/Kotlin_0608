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


class SearchActivity3 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)


        val adapter = SearchAdapter()
        searchRecyclerView.adapter = adapter

        searchButton.setOnClickListener {

        }

    }
}