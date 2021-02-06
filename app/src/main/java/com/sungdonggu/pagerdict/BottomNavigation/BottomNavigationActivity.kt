package com.sungdonggu.pagerdict.BottomNavigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sungdonggu.pagerdict.R
import kotlinx.android.synthetic.main.activity_bottom_navigation.*

class BottomNavigationActivity : AppCompatActivity(),
    BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var sortDictionaryFragment: SortDictionaryFragment
    private lateinit var sortNewsFragment: SortNewsFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_navigation)
        sorted_bottom_nav.setOnNavigationItemSelectedListener(this@BottomNavigationActivity)


        // set default view
        sortDictionaryFragment = SortDictionaryFragment.newInstance()
        supportFragmentManager.beginTransaction().add(R.id.frame_sorted, sortDictionaryFragment)
            .commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.sorted_dictionary -> {
                sortDictionaryFragment = SortDictionaryFragment.newInstance()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_sorted, sortDictionaryFragment).commit()
            }
            R.id.sorted_news -> {
                sortNewsFragment = SortNewsFragment.newInstance()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_sorted, sortNewsFragment).commit()
            }
        }
        return true
    }
}