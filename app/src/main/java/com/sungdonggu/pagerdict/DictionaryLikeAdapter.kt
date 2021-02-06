package com.sungdonggu.pagerdict

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sungdonggu.pagerdict.SQL.LikeDatabase
import kotlinx.android.synthetic.main.dictionary_content_item.view.*
import kotlinx.android.synthetic.main.dictionary_like_item.view.*

class DictionaryLikeAdapter() : RecyclerView.Adapter<DictionaryLikeAdapter.LikeHolder>() {

    companion object {
        private const val TAG = "TEST_TAG"
    }

    val list = ArrayList<LikeDatabase>()

    class LikeHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var dict_like_expandable_linearlayout = itemView.dict_like_expandable_linearlayout
        var dict_like_expandable_layout = itemView.dict_like_expandable_layout
        fun initializeDict(dict: LikeDatabase) {
            if(dict!=null){
                itemView.dict_content_tv_word!!.setText(dict.word)
                itemView.dict_content_tv_def!!.setText(dict.def)
            } else {
                itemView.dict_content_tv_word.text = "sorry"
                itemView.dict_content_tv_def.text = "sorry"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LikeHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.dictionary_like_item, parent, false)
        return LikeHolder(view)
    }

    override fun onBindViewHolder(holder: LikeHolder, position: Int) {
        val dict = list.get(position)
        holder.initializeDict(dict)

        holder.dict_like_expandable_layout.visibility =
            if (list.get(position).expandable) View.VISIBLE else View.GONE
        holder.dict_like_expandable_linearlayout.setOnClickListener {
            val dicts = list.get(position)
            dicts.expandable = !dicts.expandable
            notifyItemChanged(position)
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }
}