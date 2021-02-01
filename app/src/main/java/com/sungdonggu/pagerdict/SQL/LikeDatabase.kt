package com.sungdonggu.pagerdict.SQL

data class LikeDatabase(
    var id: Long?,
    var word: String,
    var def: String,
    var expandable: Boolean = false
)