package com.sungdonggu.pagerdict.Dictionary

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sungdonggu.pagerdict.R
import kotlinx.android.synthetic.main.dictionary_item.view.*

class DictionaryAdapter() : RecyclerView.Adapter<DictionaryAdapter.PureHolder>() {

    companion object {
        private const val TAG = "PURE_TAG"
    }

    var pureList = ArrayList<DictionaryDatabase>()

    class PureHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var pure_dictionary_expandable_linearlayout =
            itemView.pure_dictionary_expandable_linearlayout
        var pure_dictionary_expandable_layout = itemView.pure_dictionary_expandable_layout
        fun initializePureDict(pureList: ArrayList<DictionaryDatabase>) {
//            itemView.tv_pure_dictionary_word.text = "${data.word}"
//            itemView.tv_pure_dictionary_def.text = "${data.def}"
            itemView.tv_pure_dictionary_word.text = pureList.get(position).word
            itemView.tv_pure_dictionary_def.text = pureList.get(position).def
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PureHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.dictionary_item, parent, false)
        return PureHolder(view)
    }

    override fun onBindViewHolder(holder: PureHolder, position: Int) {
        val dict = pureList.get(position)
        holder.initializePureDict(pureList) // 수정
        holder.pure_dictionary_expandable_layout.visibility =
            if (pureList.get(position).expandable) View.VISIBLE else View.GONE
        holder.pure_dictionary_expandable_linearlayout.setOnClickListener {
            val datas = pureList.get(position)
            datas.expandable = !datas.expandable
            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int {
        return pureList.size
    }
}