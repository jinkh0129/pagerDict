package com.sungdonggu.pagerdict.SQL

data class ContentDictionaryDatabase(
    var id: Long?,
    var word: String,
    var def: String,
    var expandable: Boolean = false
)