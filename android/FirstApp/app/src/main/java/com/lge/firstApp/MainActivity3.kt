package com.lge.firstApp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE
import androidx.fragment.app.commit
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.fragment_main.button

class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        //-----------
        // Step 1.
        setSupportActionBar(toolbar)
        //-----------

        val fragment = FirstFragment3()
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

    // Back Button 처리 로직
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }


        return super.onOptionsItemSelected(item)
    }

}

// Back Button을 통해 이전의 fragment로 돌아갈 수 있도록 해준다.
// addToBackStack(null)

class FirstFragment3 : Fragment() {
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
                replace(R.id.mainFrame, SecondFragment3())
                addToBackStack(null)
                // Back Button을 통해 이전의 fragment로 돌아갈 수 있도록 해준다.
            }
        }
    }
}

class SecondFragment3 : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_main, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //----------------
        // Step 2
        // (activity as AppCompatActivity)
        // 위처럼 사용하면, BadCastException의 위험이 있습니다.

        (activity as? AppCompatActivity)?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //----------------


        button.text = "Second"
        button.setOnClickListener {
            parentFragmentManager.commit {
                replace(R.id.mainFrame, ThirdFragment3())
                addToBackStack(null)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        // Back Button이 사라지지 않기 때문에, 직접 처리해야 한다.
        (activity as? AppCompatActivity)?.supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }


}

class ThirdFragment3 : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_main, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button.text = "Third"
        button.setOnClickListener {
            parentFragmentManager.commit {
                replace(R.id.mainFrame, ForthFragment3())
                addToBackStack(null)
            }
        }
    }
}

class ForthFragment3 : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_main, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button.text = "Forth"
        button.setOnClickListener {

        }
    }
}