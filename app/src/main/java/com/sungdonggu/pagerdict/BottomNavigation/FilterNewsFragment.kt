package com.sungdonggu.pagerdict.BottomNavigation

class FilterNewsFragment(
    filterList: ArrayList<NewsItemFragment>,
    private val adapterSortedNewsFragment: AdapterSortedNewsFragment
) : android.widget.Filter() {

    private var filterList: ArrayList<NewsItemFragment> = filterList
    override fun performFiltering(constraint: CharSequence?): FilterResults {
        var constraint1: CharSequence? = constraint
        val results = FilterResults()

        if (constraint1 != null && constraint1.isNotEmpty()) {
            constraint1 = constraint1.toString().toUpperCase()

            val filteredNews = ArrayList<NewsItemFragment>()
            for (i in filterList.indices) {
                if (filterList[i].title!!.toUpperCase().contains(constraint1)) {
                    filteredNews.add(filterList[i])
                }
            }
            results.count = filteredNews.size
            results.values = filteredNews
        } else {
            results.count = filterList.size
            results.values = filterList
        }
        return results
    }

    override fun publishResults(constraint: CharSequence?, results: FilterResults) {
        adapterSortedNewsFragment.itemList = results.values as ArrayList<NewsItemFragment>
        adapterSortedNewsFragment.notifyDataSetChanged()
    }
}