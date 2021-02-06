package com.sungdonggu.pagerdict

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.DateFormat
import java.text.Format
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.logging.Formatter

@RequiresApi(Build.VERSION_CODES.O)
fun main() {
    val cal = Calendar.getInstance()
    cal.time = Date()
    val df: DateFormat = SimpleDateFormat("yyyy-MM-dd")
    println("current : ${df.format(cal.time)}") // 2021-01-31
    cal.add(Calendar.MONTH, -1)
    println("after : ${df.format(cal.time)}") // 2021-12-31

    val dateAndTime: LocalDateTime = LocalDateTime.now()
    val formater1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    val formater2 = DateTimeFormatter.ofPattern("yyyy년MM월dd일 HH시mm분")
    val formater3 = DateTimeFormatter.ofPattern("yyyy년 M월 d일 HH:mm")
    val onlyDate: LocalDate = LocalDate.now()
    val formatted1 = dateAndTime.format(formater1)
    val formatted2 = dateAndTime.format(formater2)
    val formatted3 = dateAndTime.format(formater3)

    println("Current date and time : ${dateAndTime}") // 2021-01-31T23:21:36.030
    println("Current date : ${onlyDate}") // 2021-01-31
    println("Current formatted1 : $formatted1") // 2021-01-31 23:21:36
    println("Current formatted2 : $formatted2") // 2021-01-31 23:21:36
    println("Current formatted3 : $formatted3") // 2021-01-31 23:21:36

    val ex1 = "2021.02.02 오전 5:07" // 18자
    val ex2 = "2021.02.02 오후 11:07" // 19자
    println("ex1 : ${ex1}")
    println("ex1.length :${ex1.length}")
    val ex1_year = ex1.substring(0,4)
    val ex1_month = ex1.substring(5,7)
    val ex1_day = ex1.substring(8,10)
    val ex1_breakOrNoon = ex1.substring(11)
//    val ex1_hour = ex1.substring(14,15)
//    val ex1_minute = ex1.substring(16,18)
    println("ex1_year : ${ex1_year}")
    println("ex1_month : ${ex1_month}")
    println("ex1_day : ${ex1_day}")
    println("ex1_breakOrNoon : ${ex1_breakOrNoon}")

    val ex1_date = ex1_year+"년 "+ex1_month+"월 "+ex1_day+"일 "+ex1_breakOrNoon
    println(ex1_date)

//    val arr = ArrayList<String>()
//    arr.add("HERE")
//    arr.add("real")
//    arr.add("SOCCER")
//    arr.add("ATM")
//    println(arr)
//    val reversearr = arr.reversed()
//    println(reversearr)

}