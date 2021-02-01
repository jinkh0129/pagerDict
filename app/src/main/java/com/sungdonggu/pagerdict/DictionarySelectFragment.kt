package com.sungdonggu.pagerdict

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class DictionarySelectFragment : Fragment() {
    companion object {
        fun newInstance(): DictionarySelectFragment {
            return DictionarySelectFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_select_dictionary, container, false)
        var tab_layout = view.findViewById<TabLayout>(R.id.tab_layout)
        var view_pager = view.findViewById<ViewPager>(R.id.view_pager)

        tab_layout.addTab(tab_layout.newTab().setText("경제용어사전"))
        tab_layout.addTab(tab_layout.newTab().setText("즐겨찾기"))

        val pagerAdapter = PagerAdapter(fragmentManager!!, 2)
        view_pager.adapter = pagerAdapter

        tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                view_pager.setCurrentItem(tab!!.position)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
        view_pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tab_layout))




        return view
    }

    class PagerAdapter(
        fragmentManager: FragmentManager,
        val tabCount: Int
    ) : FragmentStatePagerAdapter(fragmentManager) {
        override fun getCount(): Int {
            return tabCount
        }

        override fun getItem(position: Int): Fragment {
            when (position) {
                0 -> {
                    return DictionartContentFragment()
                }
                1 -> {
                    return DictionaryLikeFragment()
                }
                else -> {
                    return DictionartContentFragment()
                }
            }
        }
    }
}