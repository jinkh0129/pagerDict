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
import org.w3c.dom.Text

class PythonActivity : AppCompatActivity() {

    private lateinit var Et1: EditText
    private lateinit var Et2: EditText
    private lateinit var Btn: Button
    private lateinit var tv: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_python)

        Et1 = findViewById(R.id.et1)
        Et2 = findViewById(R.id.et2)
        Btn = findViewById(R.id.btn)
        tv = findViewById(R.id.text_view)

        if (!Python.isStarted()) {
            Python.start(AndroidPlatform(this))
        }
        val py: Python = Python.getInstance()
        val pyobj: PyObject = py.getModule("script") // here we will give name of our python file

        // now, give reference to our UI elements
        Btn.setOnClickListener {
            //function name, argument1(first number), argument2(second number)
            val obj: PyObject = pyobj.callAttr("main", Et1.text.toString(), Et2.text.toString())

            // now obj will contain our result, so set its result to textView
            tv.setText(obj.toString())

            // code is complete, lets run and check it!~
        }
    }
}