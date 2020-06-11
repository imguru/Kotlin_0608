package com.lge.firstApp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.commit
import kotlinx.android.synthetic.main.fragment_main.*

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

// 3. Fragment
// MainFragment.kt
class MainFragment : Fragment() {
    // var activity: String? = null

    // Inflating - XML
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button.setOnClickListener {

            parentFragmentManager.commit {
                replace(R.id.mainFrame, SecondFragment())
            }

            /*
            // ?: return
            val activity = activity ?: return@setOnClickListener

            // val activity = activity ?: return@foo
            activity.supportFragmentManager.beginTransaction()
                .replace(R.id.mainFrame, SecondFragment())
                .commit()
           */

            /*
            // ?.let
            activity?.let { activity ->
                activity.supportFragmentManager.beginTransaction()
                    .replace(R.id.mainFrame, SecondFragment())
                    .commit()
            }
            */

            // ?.
            /*
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.mainFrame, SecondFragment())
                ?.commit()
            */


        }
    }
}

class SecondFragment : Fragment() {
    // Inflating - XML
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button.setOnClickListener {
            parentFragmentManager.commit {
                replace(R.id.mainFrame, MainFragment())
            }
        }
    }
}












