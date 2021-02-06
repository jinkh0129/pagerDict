package com.sungdonggu.pagerdict.Dictionary

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.sungdonggu.pagerdict.R
import kotlinx.android.synthetic.main.fragment_pure_dictionary.*

class DictionaryFragment : Fragment() {
    companion object {
        private const val TAG = "pure"
        fun newInstance(): DictionaryFragment {
            return DictionaryFragment()
        }
    }

    private lateinit var pureDatabase: FirebaseDatabase
    private lateinit var pureReference: DatabaseReference
    private lateinit var rv_pure_dict_fragment: RecyclerView
    private lateinit var helper: DictionaryHelper
    private lateinit var pureDictList: ArrayList<DictionaryDatabase>

    val DB_NAME = "pure_dictionary"
    val DB_VERSION = 1
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_pure_dictionary, container, false)

        pureDatabase = FirebaseDatabase.getInstance()
        pureReference = pureDatabase.getReference("한국은행 경제용어사전")
        rv_pure_dict_fragment = view.findViewById(R.id.rv_pure_dict_fragment)

        helper = DictionaryHelper(context!!, DB_NAME, DB_VERSION)
        startFragment()

        val pureAdapter = DictionaryAdapter()
        val dicts = helper.selectPure()
        pureAdapter.pureList.addAll(dicts)
        rv_pure_dict_fragment.adapter = pureAdapter
        rv_pure_dict_fragment.setHasFixedSize(true)



        return view
    }

    fun startFragment() {
        Thread(Runnable {
            /****************************************************************************************************************************************************************/
            /*******************************************Firebase에서 단어들을 가져와서 SQLite에 저장하는 과정**********************************************************************/
            pureReference.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        for (data in snapshot.children) {
                            val dict = DictionaryDatabase(
                                null,
                                data.child("word").value.toString(),
                                data.child("content").value.toString()
                            )
                            helper.insertPure(dict) // DB에 입력하는 과정 --> 혹시 SEARCH를 하면 DB를 지워줘야하나?
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show()
                }
            })
            /**************************************************************************************************************************************************************/
            /**************************************************************************************************************************************************************/


            if (sv_pure_dict_fragment != null) {
                sv_pure_dict_fragment.setOnQueryTextListener(object :
                    androidx.appcompat.widget.SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        if (query != null) {
                            pureSearchWord(query)
                        }
                        return true
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        if (newText != null) {
                            pureSearchWord(newText)
                        }
                        return true
                    }

                })
            }
        }).start()

    }


    fun pureSearchWord(str: String) {
        val dicts = helper.selectSpecific(str)
        val pureAdapter = DictionaryAdapter()
        pureAdapter.pureList.addAll(dicts)
        rv_pure_dict_fragment.adapter = pureAdapter
        rv_pure_dict_fragment.setHasFixedSize(true)

//        for (fragObj in pureDictList) {
//            if (fragObj.word?.toLowerCase()?.contains(str.toLowerCase()) == true) {
//                val listPure = ArrayList<NoneDatabase>()
//                val none = NoneDatabase(null, fragObj.word, fragObj.def)
//                listPure.add(none)
//                val pureAdapterClass = DictionarySearchAdapter(listPure)
//                rv_pure_dict_fragment.adapter = pureAdapterClass
//                rv_pure_dict_fragment.setHasFixedSize(true)
//            }
//
//        }

    }
}