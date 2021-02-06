package com.sungdonggu.pagerdict.Kotlin.Sort

import java.util.logging.Filter

class FilterItem(filterList: ArrayList<ModelItem>, private val adapterItem: AdapterItem) :
    android.widget.Filter() {

    private var filterList: ArrayList<ModelItem> = filterList

    // filter records
    override fun performFiltering(constraint: CharSequence?): FilterResults {
        var constraint1: CharSequence? = constraint
        val results = FilterResults()

        // check constraint validity, proceed filter if search is not null/empty
        if (constraint1 != null && constraint1.isNotEmpty()) {
            // to make it case no sensitive make string uppercase
            constraint1 = constraint1.toString().toUpperCase()
            // to store filtered data
            val filteredModels = ArrayList<ModelItem>()
            for (i in filterList.indices) {
                // check if searched item matches any of items in list
                if (filterList[i].title.toUpperCase().contains(constraint1)) {
                    // search item matches, add in filteredModels
                    filteredModels.add(filterList[i])
                }
            }
            results.count = filteredModels.size
            results.values = filteredModels

        } else {
            // search is empty, show original list
            results.count = filterList.size
            results.values = filterList
        }
        return results
    }

    override fun publishResults(constraint: CharSequence, results: FilterResults) {
        adapterItem.itemList = results.values as ArrayList<ModelItem>

        adapterItem.notifyDataSetChanged()

    }
}