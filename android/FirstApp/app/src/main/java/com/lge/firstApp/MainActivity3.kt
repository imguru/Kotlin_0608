package com.lge.firstApp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val fragment = MainFragment2()

        /*
        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction()
            .replace(R.id.mainFrame, fragment)
            .commit()
        */

        supportFragmentManager.commit {
            replace(R.id.mainFrame, fragment)
        }
    }
}