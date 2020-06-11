package com.lge.firstApp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import kotlinx.android.synthetic.main.fragment_main.button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val fragment = FirstFragment()
        supportFragmentManager.commit {
            replace(R.id.mainFrame, fragment)
        }
    }


    override fun onBackPressed() {
        // backStack에 몇개의 Fragment가 남아있는지 체크하면 된다.
        val count = supportFragmentManager.backStackEntryCount
        if (count == 0) {

            val alert = AlertDialog.Builder(this).apply {
                setMessage("Quit ??")
                setPositiveButton("Yes") { _, _ ->
                    super.onBackPressed()
                }
                setNegativeButton("No") { _, _ ->
                }
            }.create()

            alert.show()

        } else {
            super.onBackPressed()   // default action
        }
    }

}

// Back Button을 통해 이전의 fragment로 돌아갈 수 있도록 해준다.
// addToBackStack(null)

class FirstFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_main, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button.text = "First"
        button.setOnClickListener {
            parentFragmentManager.commit {
                replace(R.id.mainFrame, SecondFragment())
                addToBackStack(null)
                // Back Button을 통해 이전의 fragment로 돌아갈 수 있도록 해준다.
            }
        }
    }
}

class SecondFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_main, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button.text = "Second"
        button.setOnClickListener {
            parentFragmentManager.commit {
                replace(R.id.mainFrame, ThirdFragment())
                addToBackStack(null)
            }
        }
    }
}

class ThirdFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_main, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button.text = "Third"
        button.setOnClickListener {

        }
    }
}