package com.sungdonggu.pagerdict.BottomNavigation

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import com.sungdonggu.pagerdict.NewsWithSort.AdapterSortedNews
import com.sungdonggu.pagerdict.NewsWithSort.NewsItem
import com.sungdonggu.pagerdict.R
import com.sungdonggu.pagerdict.databinding.FragmentSortedNewsBinding
import kotlinx.android.synthetic.main.fragment_sorted_news.view.*

class SortNewsFragment : Fragment() {

//    private lateinit var binding: FragmentSortedNewsBinding

    private lateinit var itemList: ArrayList<NewsItemFragment>
    private lateinit var adapterSortedNewsFragment: AdapterSortedNewsFragment
    private lateinit var newsDatabase: FirebaseDatabase
    private lateinit var newsReference: DatabaseReference

    companion object {
        fun newInstance(): SortNewsFragment {
            return SortNewsFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        binding = FragmentSortedNewsBinding.inflate(layoutInflater)
        val view: View = inflater.inflate(R.layout.fragment_sorted_news, container, false)
        newsDatabase = FirebaseDatabase.getInstance()
        newsReference = newsDatabase.getReference("News/20210203/경제")

        loadRecyclerViewNews(view)

        return view
    }

    private fun loadRecyclerViewNews(view: View) {
        val linearLayoutManager = LinearLayoutManager(this.context)

        view.frag_sortedNewsRv.layoutManager = linearLayoutManager
        itemList = ArrayList()

        newsReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (data in snapshot.children) {
                        val model = data.getValue(NewsItemFragment::class.java)
                        itemList.add(model as NewsItemFragment)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })

        adapterSortedNewsFragment = AdapterSortedNewsFragment(this.context!!, itemList)
        view.frag_sortedNewsRv.adapter = adapterSortedNewsFragment

        sortDescending()
        adapterSortedNewsFragment.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_sorted, menu)
        val searchNewsFragment = menu.findItem(R.id.action_news_search)
        val searchViewFragment =
            searchNewsFragment?.actionView as androidx.appcompat.widget.SearchView

        searchViewFragment.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapterSortedNewsFragment.filter.filter(newText)
                return false
            }
        })
        return super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.action_news_sort) {
            sortDialog()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun sortDialog() {
        val options = arrayOf("최신날짜순", "옛날날짜순")
        val dialog = AlertDialog.Builder(this.context)
        dialog.setTitle("Sort")
            .setItems(options) { dialogInterface, i ->
                if (i == 0) {
                    dialogInterface.dismiss()
                    sortDescending()
                } else if (i == 1) {
                    dialogInterface.dismiss()
                    sortAscending()
                }
            }.show()
    }

    private fun sortAscending() {
        itemList.sortBy { it.pubTime }
        adapterSortedNewsFragment.notifyDataSetChanged()
    }

    private fun sortDescending() {
        itemList.sortByDescending { it.pubTime }
        adapterSortedNewsFragment.notifyDataSetChanged()
    }
}