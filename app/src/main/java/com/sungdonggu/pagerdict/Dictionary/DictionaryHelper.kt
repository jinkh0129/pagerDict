package com.sungdonggu.pagerdict.Dictionary

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DictionaryHelper(context: Context?, name: String, version: Int) :
    SQLiteOpenHelper(context, name, null, version) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createQuery = "CREATE TABLE pure_dictionary(" +
                "id INTEGER PRIMARY KEY," +
                "word VARCHAR(200) NOT NULL," +
                "def TEXT NOT NULL)"
        db?.execSQL(createQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    fun insertPure(data: DictionaryDatabase) {
        val wd = writableDatabase
        val values = ContentValues()

        values.put("word", data.word)
        values.put("def", data.def)

        wd.insert("pure_dictionary", null, values)
        wd.close()
    }

    fun selectPure(): MutableList<DictionaryDatabase> {
        val list = ArrayList<DictionaryDatabase>()
        val selectQuery = "SELECT * FROM pure_dictionary"
        var rd = readableDatabase

        val cursor = rd.rawQuery(selectQuery, null)
        while (cursor.moveToNext()) {
            val id = cursor.getLong(cursor.getColumnIndex("id"))
            val word = cursor.getString(cursor.getColumnIndex("word"))
            val def = cursor.getString(cursor.getColumnIndex("def"))

            val dict = DictionaryDatabase(id = id, word = word, def = def)
            list.add(dict)
        }
        cursor.close()
        rd.close()

        return list
    }

    fun selectSpecific(str: String): MutableList<DictionaryDatabase> {
        val list = ArrayList<DictionaryDatabase>()
        val selectSpecificQuery = "SELECT * FROM pure_dictionary WHERE word = '%${str}%'"
        var rd = readableDatabase

        val cursor = rd.rawQuery(selectSpecificQuery, null)
        while (cursor.moveToNext()) {
            val id = cursor.getLong(cursor.getColumnIndex("id"))
            val word = cursor.getString(cursor.getColumnIndex("word"))
            val def = cursor.getString(cursor.getColumnIndex("def"))

            val dict = DictionaryDatabase(id = id, word = word, def = def)
            list.add(dict)
        }
        cursor.close()
        rd.close()

        return list
    }

    fun deletePure() {
        val wd = writableDatabase
        val deleteQuery = "DELETE FROM pure_dictionary"
        wd.execSQL(deleteQuery)
        wd.close()
    }
}