package com.sungdonggu.pagerdict.Kotlin.Sort

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.sungdonggu.pagerdict.databinding.SortRowItemBinding

class AdapterItem : RecyclerView.Adapter<AdapterItem.HolderItem>, Filterable {
    private var context: Context
    public var itemList: ArrayList<ModelItem> // make it public to access in FilterItem class
    private var filterList: ArrayList<ModelItem>
    private var filter: FilterItem? = null

    constructor(context: Context, itemList: ArrayList<ModelItem>) : super() {
        this.context = context
        this.itemList = itemList
        this.filterList = itemList
    }


    // ViewBinding
    private lateinit var binding: SortRowItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderItem {
        // inflate layout using view binding
        binding = SortRowItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return HolderItem(binding.root)
    }

    override fun onBindViewHolder(holder: HolderItem, position: Int) {
        // get data
        val model = itemList[position]

        val title = model.title
        val description = model.description
        val image = model.image

        // set data
        holder.titleTv.text = title
        holder.descriptionTv.text = description
        holder.iconIv.setImageResource(image)

        // handle item click
        holder.itemView.setOnClickListener {
            // start data to show details of item clicked
            val intent = Intent(context, DetailsActivity::class.java)

            intent.putExtra("items", itemList)
            intent.putExtra("position", position)
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        // return size/length of list | number of records
        return itemList.size
    }

    inner class HolderItem(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var titleTv: TextView
        var descriptionTv: TextView
        var iconIv: ImageView

        init {
            binding = SortRowItemBinding.bind(itemView)

            // init UI views
            titleTv = binding.titleTv
            descriptionTv = binding.descriptionTv
            iconIv = binding.iconIv
        }
    }

    override fun getFilter(): Filter {
        if (filter == null) {
            filter = FilterItem(filterList, this)
        }
        return filter as FilterItem
    }
}