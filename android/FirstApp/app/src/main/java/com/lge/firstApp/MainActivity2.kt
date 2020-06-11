package com.lge.firstApp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

// 1.
// styles.xml
// <style name="AppTheme" parent="Theme.AppCompat.Light.NoActionBar">

// 2. activity_main2
//     toolbar
//     frameLayout - Fragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
    }
}