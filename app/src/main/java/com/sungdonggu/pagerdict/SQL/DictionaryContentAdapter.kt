package com.sungdonggu.pagerdict.SQL

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.ToggleButton
import androidx.recyclerview.widget.RecyclerView
import com.sungdonggu.pagerdict.DictionaryLikeAdapter
import com.sungdonggu.pagerdict.DictionaryLikeFragment
import com.sungdonggu.pagerdict.R
import kotlinx.android.synthetic.main.dictionary_content_item.view.*

class DictionaryContentAdapter() :
    RecyclerView.Adapter<DictionaryContentAdapter.ContentHolder>() {

    companion object {
        private const val TAG = "TEST_TAG"
    }


    var dataList = ArrayList<DictionaryDatabase>()

    class ContentHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var dict_content_expandable_linearlayout = itemView.dict_content_expandable_linearlayout
        var dict_content_expandable_layout = itemView.dict_content_expandable_layout
        var content_toggle_like = itemView.content_toggle_like as ToggleButton
        fun initializeDict(dict: DictionaryDatabase) {
            itemView.dict_content_tv_word.text = "${dict.word}"
            itemView.dict_content_tv_def.text = "${dict.def}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.dictionary_content_item, parent, false)
        return ContentHolder(view)
    }

    override fun onBindViewHolder(holder: ContentHolder, position: Int) {
        val dict = dataList.get(position)
        holder.initializeDict(dict)

        holder.dict_content_expandable_layout.visibility =
            if (dataList.get(position).expandable) View.VISIBLE else View.GONE
        holder.dict_content_expandable_linearlayout.setOnClickListener {
            val dicts = dataList.get(position)
            dicts.expandable = !dicts.expandable
            notifyItemChanged(position)
        }

        holder.content_toggle_like.setOnClickListener {
            val dicts = dataList.get(position)
            val likeFragment: DictionaryLikeFragment = DictionaryLikeFragment()
            val bundle: Bundle = Bundle()
            if (holder.content_toggle_like.isChecked) {
                bundle.putString("like_word", dicts.word)
                bundle.putString("like_def", dicts.def)
                likeFragment.arguments = bundle
            } else {
                bundle.putString("dislike_word", dicts.word)
                bundle.putString("dislike_def", dicts.def)
                likeFragment.arguments = bundle
            }
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}