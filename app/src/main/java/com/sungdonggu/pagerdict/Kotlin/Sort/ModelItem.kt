package com.sungdonggu.pagerdict.Kotlin.Sort

import java.io.Serializable

class ModelItem(
    var title: String, var description: String, var image: Int
) : Serializable // made serializable to pass object to next activity to show item details