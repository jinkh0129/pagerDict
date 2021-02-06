package com.sungdonggu.pagerdict.NewsWithSort

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sungdonggu.pagerdict.R
import com.sungdonggu.pagerdict.databinding.SortedNewsItemBinding

class AdapterSortedNews : RecyclerView.Adapter<AdapterSortedNews.HolderNews>, Filterable {
    private var context: Context
    public var itemList: ArrayList<NewsItem>
    private var filterList: ArrayList<NewsItem>
    private var filter: FilterNews? = null

    constructor(context: Context, itemList: ArrayList<NewsItem>) : super() {
        this.context = context
        this.itemList = itemList
        this.filterList = itemList
    }

    // view binding
    private lateinit var binding: SortedNewsItemBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdapterSortedNews.HolderNews {
        binding = SortedNewsItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return HolderNews(binding.root)
    }

    override fun onBindViewHolder(holder: AdapterSortedNews.HolderNews, position: Int) {
        val model = itemList[position]

        val headline = model.title
        val date = model.pubTime

        holder.headlineTv.text = headline
        holder.dateTv.text = date
        Glide.with(holder.itemView)
            .load(model.imgSrc)
            .placeholder(R.drawable.common_google_signin_btn_icon_disabled)
            .error(R.drawable.common_google_signin_btn_icon_disabled)
            .fallback(R.drawable.common_google_signin_btn_icon_disabled)
            .into(holder.thumbIv)
        holder.itemView.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(model.link))
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class HolderNews(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var headlineTv: TextView
        var thumbIv: ImageView
        var dateTv: TextView

        init {
            binding = SortedNewsItemBinding.bind(itemView)

            headlineTv = binding.headlineTv
            thumbIv = binding.thumbIv
            dateTv = binding.dateTv
        }
    }

    override fun getFilter(): Filter {
        if (filter == null) {
            filter = FilterNews(filterList, this)
        }
        return filter as FilterNews
    }
}