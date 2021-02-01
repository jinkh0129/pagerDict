package com.sungdonggu.pagerdict

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

fun main() {
    val cal = Calendar.getInstance()
    cal.time = Date()
    val df: DateFormat = SimpleDateFormat("yyyy-MM-dd")
    println("current : ${df.format(cal.time)}") // 2021-01-31
    cal.add(Calendar.MONTH, -1)
    println("after : ${df.format(cal.time)}") // 2021-12-31

    val dateAndTime: LocalDateTime = LocalDateTime.now()
    val formater = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    val onlyDate: LocalDate = LocalDate.now()
    val formatted = dateAndTime.format(formater)

    println("Current date and time : ${dateAndTime}") // 2021-01-31T23:21:36.030
    println("Current date : ${onlyDate}") // 2021-01-31
    println("Current : $formatted") // 2021-01-31 23:21:36
}