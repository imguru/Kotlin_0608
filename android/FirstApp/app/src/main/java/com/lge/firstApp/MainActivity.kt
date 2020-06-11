package com.lge.firstApp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


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

// Application Context
//        |
// Activity Context


// SAM(Single Abstract Method)
// => 자바의 인터페이스 중에 메소드가 한개인 경우, 익명 객체가 아닌 '람다 표현식'으로 사용할 수 있다.

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // import kotlinx.android.synthetic.main.activity_main.*  확인해야 합니다.


        // Kotlin에서는 findViewById를 하지 않아도, 자동으로 view id의 변수를 생성해준다. - kotlin-android-extensions
        // android:id="@+id/helloButton"   - Kotlin
        // android:id="@+id/button_hello"  - Java

        // 장점: this가 MainActivity로 바인드된다.
        // Rename: Shift + F6
        button.setOnClickListener {
            // textView.text = "Clicked"
            // Toast.makeText(this, "Hello!", Toast.LENGTH_SHORT).show()

            // Activity전환을 위해서는 Intent를 사용해야 합니다.
            // => Intent에서 class 인자를 전달할 때, java의 클래스 정보를 전달해야 합니다.
            // SecondActivity::class       - Kotlin's class
            // SecondActivity::class.java  - Java's class     => Android

            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }

    }


    /*
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button: Button = findViewById(R.id.button)
        val textView = findViewById<TextView>(R.id.textView)

        button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                // textView.setText("Clicked")
                textView.text = "Clicked"
                Toast.makeText(this@MainActivity, "Hello!", Toast.LENGTH_SHORT).show()
                // this: 익명 객체에 대한 참조
                // this@MainActivity: 외부 MainActivity에 대한 참조
            }
        })
    }
    */
}

// SecondActivity.kt
// => AndriodManifest.xml에 Activity에 대한 정보를 추가해야 한다.
//    <activity android:name=".SecondActivity" />
class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
    }
}










