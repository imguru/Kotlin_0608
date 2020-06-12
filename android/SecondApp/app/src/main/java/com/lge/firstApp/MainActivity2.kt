package com.lge.firstApp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.lge.firstApp.model.User
import com.lge.firstApp.net.githubApi
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {

            val call = githubApi.getUser("JakeWharton")
            call.enqueue(object : Callback<User> {
                override fun onFailure(call: Call<User>, t: Throwable) {
                    Toast.makeText(
                        this@MainActivity2,
                        "Err - ${t.localizedMessage}",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if (!response.isSuccessful)
                        return


                    val user = response.body() ?: return
                    nameTextView.text = user.name
                    GlideApp.with(this@MainActivity2)
                        .load(user.avatarUrl)
                        .avatar()
                        .into(avatarImageView)

                }
            })
        }
    }
}


















