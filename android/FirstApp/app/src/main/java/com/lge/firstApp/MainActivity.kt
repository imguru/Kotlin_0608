package com.lge.firstApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

// 코틀린 기반 안드로이드 프로젝트
//  - Gradle

// Project             : build.gradle (프로젝트 수준 build.gradle)
//   - Module(app)     : build.gradle (모듈 수준 build.gradle)

// project 수준 build.gradle
// 1. ext.kotlin_version = "1.3.72"
// 2. classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version"

// app 수준 build.gradle
//  1. apply plugin: 'kotlin-android'
//  2. apply plugin: 'kotlin-android-extensions'

//  3. dependencies
//     - implementation "org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version"
//     - implementation 'androidx.core:core-ktx:1.3.0'

// Android에서 Java8을 사용하는 방법
// compileOptions {
//        sourceCompatibility JavaVersion.VERSION_1_8
//        targetCompatibility JavaVersion.VERSION_1_8
// }

// Kotlin이 Java8을 사용하는 방법
// kotlinOptions {
//        jvmTarget = JavaVersion.VERSION_1_8.toString()
// }

// Java Version
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button: Button = findViewById(R.id.button)
        val textView = findViewById<TextView>(R.id.textView)

        button.setOnClickListener(object: View.OnClickListener {
            override fun onClick(v: View?) {
                textView.text = "Clicked"
            }
        })

    }
}












