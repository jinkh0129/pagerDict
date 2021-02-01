package com.sungdonggu.pagerdict.Python

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.chaquo.python.PyObject
import com.chaquo.python.Python
import com.chaquo.python.android.AndroidPlatform
import com.sungdonggu.pagerdict.R

class CodeActivity : AppCompatActivity() {

    private lateinit var et_code: EditText
    private lateinit var samsung: Button
    private lateinit var kia: Button
    private lateinit var doosan: Button
    private lateinit var tv_code: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_code)

        samsung = findViewById(R.id.samsung)
        kia = findViewById(R.id.kia)
        doosan = findViewById(R.id.doosan)
        tv_code = findViewById(R.id.tv_code)

        if (!Python.isStarted()) {
            Python.start(AndroidPlatform(this))
        }
        val py: Python = Python.getInstance()
        val pyobj: PyObject = py.getModule("getPrice") // here we will give name of our python file

        samsung.setOnClickListener {
            val obj: PyObject = pyobj.callAttr("getPrice", 5930)
            tv_code.setText(obj.toString())
        }
        kia.setOnClickListener {
            val obj: PyObject = pyobj.callAttr("getPrice", 270)
            tv_code.setText(obj.toString())
        }
        doosan.setOnClickListener {
            val obj: PyObject = pyobj.callAttr("getPrice", 34020)
            tv_code.setText(obj.toString())
        }
    }
}