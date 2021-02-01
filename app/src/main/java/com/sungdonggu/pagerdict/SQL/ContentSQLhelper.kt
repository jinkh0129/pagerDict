package com.sungdonggu.pagerdict.SQL

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ContentSQLhelper(context: Context?, name: String, version: Int) :
    SQLiteOpenHelper(context, name, null, version) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createQuery = "CREATE TABLE dictionary(" +
                "id INTEGER PRIMARY KEY," +
                "word VARCHAR(200) NOT NULL," +
                "def TEXT NOT NULL)"
        db?.execSQL(createQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    fun insertData(dict: DictionaryDatabase) {
        val wd = writableDatabase
        val values = ContentValues()

        values.put("word", dict.word)
        values.put("def", dict.def)

        wd.insert("dictionary", null, values)
        wd.close()
    }

    fun selectData():MutableList<DictionaryDatabase>{
        val list = ArrayList<DictionaryDatabase>()

        val selectQuery = "SELECT * FROM dictionary"
        var rd = readableDatabase

        val cursor = rd.rawQuery(selectQuery, null)
        while (cursor.moveToNext()) {
            val id = cursor.getLong(cursor.getColumnIndex("id"))
            val word = cursor.getString(cursor.getColumnIndex("word"))
            val def = cursor.getString(cursor.getColumnIndex("def"))

            val dict = DictionaryDatabase(id, word, def)
            list.add(dict)
        }
        cursor.close()
        rd.close()


        return list
    }
}