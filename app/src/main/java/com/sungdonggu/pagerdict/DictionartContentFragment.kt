package com.sungdonggu.pagerdict

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.sungdonggu.pagerdict.SQL.DictionaryContentAdapter
import com.sungdonggu.pagerdict.SQL.ContentDictionaryDatabase
import com.sungdonggu.pagerdict.SQL.ContentSQLhelper
import kotlinx.android.synthetic.main.fragment_real_dictionary.*

class DictionartContentFragment : Fragment() {
    companion object {
        private const val TAG = "TEST_TAG"
    }

    private lateinit var dictDatabase: FirebaseDatabase
    private lateinit var dictReference: DatabaseReference
    private lateinit var rv_dict_content_fragment: RecyclerView
    var listDict: MutableList<ContentDictionaryDatabase> = mutableListOf()
    private lateinit var helper: ContentSQLhelper
    private lateinit var wordDictList: ArrayList<ContentDictionaryDatabase>

    val DB_NAME = "dictionary"
    val DB_VERSION = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_real_dictionary, container, false)
        dictDatabase = FirebaseDatabase.getInstance()
        dictReference = dictDatabase.getReference("한국은행 경제용어사전")
        rv_dict_content_fragment = view.findViewById(R.id.rv_dict_content_fragment)

        helper = ContentSQLhelper(context!!, DB_NAME, DB_VERSION)

        startFragment()
        val adapter = DictionaryContentAdapter()
        val dicts = helper.selectData()
        adapter.dataList.addAll(dicts)
        rv_dict_content_fragment.adapter = adapter
        rv_dict_content_fragment.setHasFixedSize(true)

        return view
    }

    fun startFragment() {
        if (dictReference != null) {
            dictReference.addValueEventListener((object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        for (data in snapshot.children) {
                            val dict = ContentDictionaryDatabase(
                                null,
                                data.child("word").value.toString(),
                                data.child("content").value.toString()
                            )
                            helper.insertData(dict)
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show()
                }
            }))
        }
        if (sv_dict_content_fragment != null) {
            sv_dict_content_fragment.setOnQueryTextListener(object :
                androidx.appcompat.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if (query != null) {
                        searchWord(query)
                    }
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if (newText != null) {
                        searchWord(newText)
                    }
                    return true
                }
            })
        }
    }

    private fun searchWord(str: String) {
        var wordList: ArrayList<ContentDictionaryDatabase> = ArrayList()
        var fragObj: ContentDictionaryDatabase
        for (fragObj in wordDictList) {
            if (fragObj.word?.toLowerCase()?.contains(str.toLowerCase()) == true) {
                wordList.add(fragObj)
                helper.insertData(wordList as ContentDictionaryDatabase)
            }
        }
        val fragAdapterClass = DictionaryContentAdapter()
        val dicts = helper.selectData()
        fragAdapterClass.dataList.addAll(dicts)
        rv_dict_content_fragment.adapter = fragAdapterClass
        rv_dict_content_fragment.setHasFixedSize(true)
    }
}

