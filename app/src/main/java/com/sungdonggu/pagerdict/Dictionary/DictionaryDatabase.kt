package com.sungdonggu.pagerdict.Dictionary

data class DictionaryDatabase(
    var id: Long?,
    var word: String?,
    var def: String?,
    var expandable: Boolean = false
)