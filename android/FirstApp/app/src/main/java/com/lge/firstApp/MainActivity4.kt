package com.lge.firstApp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
    }
}

// MDC - Material Design Component
//   https://github.com/material-components/material-components-android

// 1. app수준의 build.gradle 의존성에
//  implementation 'com.google.android.material:material:<version>'

// 2. Theme 적용(styles.xml)


// https://medium.com/androiddevelopers/migrating-to-material-components-for-android-ec6757795351