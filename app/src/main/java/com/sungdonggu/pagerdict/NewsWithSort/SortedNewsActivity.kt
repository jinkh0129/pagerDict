package com.sungdonggu.pagerdict.NewsWithSort

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import com.sungdonggu.pagerdict.R
import com.sungdonggu.pagerdict.databinding.ActivitySortedNewsBinding

class SortedNewsActivity : AppCompatActivity() {

    // view binding
    private lateinit var binding: ActivitySortedNewsBinding

    private lateinit var itemList: ArrayList<NewsItem>
    private lateinit var adapterSortedNews: AdapterSortedNews
    private lateinit var newsDatabase: FirebaseDatabase
    private lateinit var newsReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySortedNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Thread(Runnable {
            newsDatabase = FirebaseDatabase.getInstance()
            newsReference = newsDatabase.getReference("News/20210202/경제")

            loadRecyclerViewNews()
        }).start()
    }

    private fun loadRecyclerViewNews() {
        val linearLayoutManager = LinearLayoutManager(this)
        // set layout to recyclerView
        binding.sortedNewsRv.layoutManager = linearLayoutManager

        // initialize list of items
        itemList = ArrayList()

        // load items
//        if (newsReference != null) {
//
//        }
        newsReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (data in snapshot.children) {
                        val model = data.getValue(NewsItem::class.java)
                        itemList.add(model as NewsItem)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })

        // setup adapter
        adapterSortedNews = AdapterSortedNews(this, itemList)

        // set adapter to recyclerView
        binding.sortedNewsRv.adapter = adapterSortedNews

        // default sort
        sortDescending()
        adapterSortedNews.notifyDataSetChanged()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_sorted, menu)
        val searchNews = menu?.findItem(R.id.action_news_search)
        val searchView = searchNews?.actionView as androidx.appcompat.widget.SearchView

        searchView.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapterSortedNews.filter.filter(newText)
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
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

        val dialog = AlertDialog.Builder(this)
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
        adapterSortedNews.notifyDataSetChanged()
    }

    private fun sortDescending() {
        itemList.sortByDescending { it.pubTime }
        adapterSortedNews.notifyDataSetChanged()
    }
}