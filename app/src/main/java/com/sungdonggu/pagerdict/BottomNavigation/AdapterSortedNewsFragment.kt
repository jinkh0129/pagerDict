package com.sungdonggu.pagerdict.BottomNavigation

import android.content.Context
import android.content.Intent
import android.media.Image
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sungdonggu.pagerdict.R
import com.sungdonggu.pagerdict.databinding.FragmentItemNewsBinding
import com.sungdonggu.pagerdict.databinding.FragmentSortedNewsBinding

class AdapterSortedNewsFragment :
    RecyclerView.Adapter<AdapterSortedNewsFragment.FragmentNewsHolder>, Filterable {
    private var context: Context
    public var itemList: ArrayList<NewsItemFragment>
    private var filterList: ArrayList<NewsItemFragment>
    private var filter: FilterNewsFragment? = null

    constructor(context: Context, itemList: ArrayList<NewsItemFragment>) : super() {
        this.context = context
        this.itemList = itemList
        this.filterList = itemList
    }

//    private lateinit var binding: FragmentItemNewsBinding

    inner class FragmentNewsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var titleTv: TextView
        var imageIv: ImageView
        var dateTv: TextView

        init {
            titleTv = itemView.findViewById(R.id.frag_headlineTv)
            imageIv = itemView.findViewById(R.id.frag_thumbIv)
            dateTv = itemView.findViewById(R.id.frag_dateTv)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdapterSortedNewsFragment.FragmentNewsHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.fragment_item_news, parent, false)
//        binding = FragmentItemNewsBinding.inflate(LayoutInflater.from(context), parent, false)
        return FragmentNewsHolder(view)
    }

    override fun onBindViewHolder(
        holder: AdapterSortedNewsFragment.FragmentNewsHolder,
        position: Int
    ) {
        val fragmentModel = itemList[position]
        val headline = fragmentModel.title
        val date = fragmentModel.pubTime
        val image = fragmentModel.imgSrc

        holder.titleTv.text = headline
        holder.dateTv.text = date
        Glide.with(holder.itemView)
            .load(image)
            .placeholder(R.drawable.common_google_signin_btn_icon_disabled)
            .error(R.drawable.common_google_signin_btn_icon_disabled)
            .fallback(R.drawable.common_google_signin_btn_icon_disabled)
            .into(holder.imageIv)
        holder.itemView.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(fragmentModel.link))
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun getFilter(): Filter {
        if (filter == null) {
            filter = FilterNewsFragment(filterList, this)
        }
        return filter as FilterNewsFragment
    }
}