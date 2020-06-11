package com.lge.firstApp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

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

        val fragment = MainFragment()

        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction()
            .replace(R.id.mainFrame, fragment)
            .commit()
    }
}

// 3. Fragment
// MainFragment.kt
class MainFragment : Fragment() {
    // Inflating - XML
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }
}













