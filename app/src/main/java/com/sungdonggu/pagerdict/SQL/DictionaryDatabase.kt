package com.sungdonggu.pagerdict.SQL

data class DictionaryDatabase(
    var id: Long?,
    var word: String,
    var def: String,
    var expandable: Boolean = false
)