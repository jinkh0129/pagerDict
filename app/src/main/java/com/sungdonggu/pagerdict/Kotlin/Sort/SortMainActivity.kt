package com.sungdonggu.pagerdict.Kotlin.Sort

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.sungdonggu.pagerdict.R
import com.sungdonggu.pagerdict.databinding.ActivitySortMainBinding

class SortMainActivity : AppCompatActivity() {

    // viewBinding
    private lateinit var binding: ActivitySortMainBinding

    // arrays for recyclerView
    //titles
    private val titles = arrayOf(
        "Android L 5",
        "Android M 6",
        "Android N 7",
        "Android O 8",
        "Android P 9",
        "Android Q 10",
        "Android R 11",
        "Android R 11",
        "Android R 11",
        "Android R 11",
        "Android R 11",
        "Android R 11",
    )

    // descriptions
    private val descriptions = arrayOf(
        "Android 5 comes with the API 21 & 22, Code name Lollipop",
        "Android 6 comes with the API 23, Code name Marshmallow",
        "Android 7 comes with the API 24 & 25, Code name Nougat",
        "Android 8 comes with the API 26 & 27, Code name Oreo",
        "Android 9 comes with the API 28, Code name Pie",
        "Android 10 comes with the API 29, Code name Q",
        "Android 11 comes with the API 30, Code name R",
        "Android 11 comes with the API 30, Code name R",
        "Android 11 comes with the API 30, Code name R",
        "Android 11 comes with the API 30, Code name R",
        "Android 11 comes with the API 30, Code name R",
        "Android 11 comes with the API 30, Code name R",
    )

    // images
    private val images = arrayOf(
        R.drawable.android05,
        R.drawable.android06,
        R.drawable.android07,
        R.drawable.android08,
        R.drawable.android09,
        R.drawable.android10,
        R.drawable.android11,
        R.drawable.android11,
        R.drawable.android11,
        R.drawable.android11,
        R.drawable.android11,
        R.drawable.android11,
    )

    private lateinit var itemList: ArrayList<ModelItem>
    private lateinit var adapterItem: AdapterItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySortMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadRecyclerViewItems()
    }

    private fun loadRecyclerViewItems() {
//        val linearLayoutManager = LinearLayoutManager(this)
//         val gridLayoutManager = GridLayoutManager(this, 2)
        val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, 1)

        // set layout to recyclerView
        binding.itemsRv.layoutManager = staggeredGridLayoutManager

        // init list of items
        itemList = ArrayList()

        // load items
        for (i in titles.indices) {
            val model = ModelItem(titles[i], descriptions[i], images[i])
            // add to list
            itemList.add(model)
        }
        // setup adapter
        adapterItem = AdapterItem(this, itemList)
        // set adapter to recyclerView
        binding.itemsRv.adapter = adapterItem
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // inflate menu_main.xml
        menuInflater.inflate(R.menu.menu_main, menu)

        // get action_search from menu_main.xml
        val searchItem = menu?.findItem(R.id.action_search)
        // make action_search a SearchView
        val searchView = searchItem?.actionView as androidx.appcompat.widget.SearchView

        // search query listener
        searchView.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // search when search button clicked
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // search as and when type even a single letter in searchView
                adapterItem.filter.filter(newText)

                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // handle menu item clicks

        // get id of clicked menu item
        val id = item.itemId
        if (id == R.id.action_sort) {
            sortDialog()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun sortDialog() {
        // options to display in dialog
        val options = arrayOf("Ascending", "Descending")

        // init dialog
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle("Sort")
            .setItems(options) { dialogInterface, i ->
                if (i == 0) {
                    // Ascending clicked
                    dialogInterface.dismiss()
                    sortAscending()
                } else if (i == 1) {
                    // Descending clicked
                    dialogInterface.dismiss()
                    sortDescending()
                }
            }.show()
    }

    private fun sortAscending() {
        // sort ascending
        itemList.sortBy { it.title }
        // refresh adapter after sorting
        adapterItem.notifyDataSetChanged()
    }

    private fun sortDescending() {
        // sort ascending
        itemList.sortByDescending { it.title }
        // refresh adapter after sorting
        adapterItem.notifyDataSetChanged()
    }
}