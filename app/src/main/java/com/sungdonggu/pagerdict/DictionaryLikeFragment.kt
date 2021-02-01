package com.sungdonggu.pagerdict

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.sungdonggu.pagerdict.SQL.ContentSQLhelper
import com.sungdonggu.pagerdict.SQL.LikeDatabase
import com.sungdonggu.pagerdict.SQL.LikeSQLhelper
import kotlinx.android.synthetic.main.dictionary_content_item.*
import kotlinx.android.synthetic.main.fragment_like_dictionary.*

class DictionaryLikeFragment : Fragment() {

    val DB_NAME = "like_dictionary"
    val DB_VERSION = 1

    companion object {
        private const val TAG = "TEST_TAG"
    }

    private lateinit var rv_dict_like_fragment: RecyclerView
    private lateinit var helper: LikeSQLhelper


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_like_dictionary, container, false)

        rv_dict_like_fragment = view.findViewById(R.id.rv_dict_like_fragment)

        val like_word = arguments?.getString("like_word")
        val like_def = arguments?.getString("like_def")
        val dislike_word = arguments?.getString("dislike_word")
        val dislike_def = arguments?.getString("dislike_def")
        Log.d(TAG,"like_word : ${like_word}")
        Log.d(TAG,"dislike_word : ${dislike_word}")

        helper = LikeSQLhelper(context!!, DB_NAME, DB_VERSION)

        val like_dict = LikeDatabase(null, like_word.toString(), like_def.toString())
        val dislike_dict = LikeDatabase(null, dislike_word.toString(), dislike_def.toString())

        helper.insertLikeData(like_dict)
        helper.deleteLikeData(dislike_dict)

        val adapter = DictionaryLikeAdapter()
        val dicts = helper.selectLikeData()
        adapter.list.addAll(dicts)
        rv_dict_like_fragment.adapter = adapter
        rv_dict_like_fragment.setHasFixedSize(true)

        return view
    }

}