package com.sungdonggu.pagerdict.Kotlin.Sort

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import com.sungdonggu.pagerdict.R
import com.sungdonggu.pagerdict.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {

    // view binding
    private lateinit var binding: ActivityDetailsBinding

    // actionbar
    private lateinit var actionBar: ActionBar

    private lateinit var itemList: ArrayList<ModelItem>
    private var positionClicked: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // init actionbar
        actionBar = supportActionBar!!
        // back button
        actionBar.setDisplayShowHomeEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)

        // get data from intent
        itemList = intent.getSerializableExtra("items") as ArrayList<ModelItem>
        positionClicked = intent.getIntExtra("position", 0) // adapter position

        // set data
        binding.titleTv.text = itemList[positionClicked].title
        binding.descriptionTv.text = itemList[positionClicked].description
        binding.iconIv.setImageResource(itemList[positionClicked].image)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed() // goto previous activity, when actionbar back button clicked
        return super.onSupportNavigateUp()
    }
}