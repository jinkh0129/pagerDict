package com.sungdonggu.pagerdict

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    private lateinit var homeFragment: HomeFragment
    private lateinit var dictionaryFragment: DictionarySelectFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottom_navigationView.setOnNavigationItemSelectedListener(this@MainActivity)

        homeFragment = HomeFragment.newInstance()
        supportFragmentManager.beginTransaction().add(R.id.frame_main, homeFragment).commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.bottom_home -> {
                homeFragment = HomeFragment.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.frame_main, homeFragment).commit()
            }
            R.id.bottom_dictionary -> {
                dictionaryFragment = DictionarySelectFragment.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.frame_main, dictionaryFragment).commit()
            }

        }
        return true
    }
}