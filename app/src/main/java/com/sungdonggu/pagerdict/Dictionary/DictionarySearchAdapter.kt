package com.sungdonggu.pagerdict.Dictionary

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sungdonggu.pagerdict.R
import kotlinx.android.synthetic.main.dictionary_item.view.*

class DictionarySearchAdapter(val dictList: ArrayList<NoneDatabase>) :
    RecyclerView.Adapter<DictionarySearchAdapter.SearchViewHolder>() {
    class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var pure_dictionary_expandable_linearlayout =
            itemView.pure_dictionary_expandable_linearlayout
        var pure_dictionary_expandable_layout = itemView.pure_dictionary_expandable_layout
        val tv_pure_dictionary_word = itemView.tv_pure_dictionary_word
        val tv_pure_dictionary_def = itemView.tv_pure_dictionary_def
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.dictionary_item, parent, false)
        return SearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.tv_pure_dictionary_word.setText(dictList[position].word)
        holder.tv_pure_dictionary_def.setText(dictList[position].def)
        holder.pure_dictionary_expandable_layout.visibility =
            if (dictList[position].expandable) View.VISIBLE else View.GONE
        holder.pure_dictionary_expandable_linearlayout.setOnClickListener {
            val datas = dictList[position]
            datas.expandable = !datas.expandable
            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int {
        return dictList.size
    }
}