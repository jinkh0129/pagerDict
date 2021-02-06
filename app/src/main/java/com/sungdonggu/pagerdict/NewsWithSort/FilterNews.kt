package com.sungdonggu.pagerdict.NewsWithSort

class FilterNews(
    filterList: ArrayList<NewsItem>,
    private val adapterSortedNews: AdapterSortedNews
) : android.widget.Filter() {

    private var filterList: ArrayList<NewsItem> = filterList

    override fun performFiltering(constraint: CharSequence?): FilterResults {
        var constraint1: CharSequence? = constraint
        val results = FilterResults()

        if (constraint1 != null && constraint1.isNotEmpty()) {

            constraint1 = constraint1.toString().toUpperCase()

            val filteredNews = ArrayList<NewsItem>()
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
        adapterSortedNews.itemList = results.values as ArrayList<NewsItem>
        adapterSortedNews.notifyDataSetChanged()
    }
}