package com.sungdonggu.pagerdict.Dictionary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sungdonggu.pagerdict.R
import kotlinx.android.synthetic.main.activity_dictionary.*

class DictionaryActivity : AppCompatActivity(),
    BottomNavigationView.OnNavigationItemSelectedListener {
    private lateinit var dictionaryFragment: DictionaryFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dictionary)

        pure_bottom_NavigationView.setOnNavigationItemSelectedListener(this@DictionaryActivity)

        dictionaryFragment = DictionaryFragment.newInstance()
        supportFragmentManager.beginTransaction()
            .replace(R.id.pure_frame_main, dictionaryFragment).commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.pure_bottom_dictionary -> {
                dictionaryFragment = DictionaryFragment.newInstance()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.pure_frame_main, dictionaryFragment).commit()
            }
        }
        return true
    }
}