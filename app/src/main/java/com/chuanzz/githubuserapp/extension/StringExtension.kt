package com.chuanzz.githubuserapp.extension

import android.content.Context
import com.chuanzz.githubuserapp.R
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.math.log10
import kotlin.math.pow

fun Long.formatFollowing(context: Context): String {
    if (this <= 9999) {
        return this.toString()
    }

    val units =
        arrayOf(
            "",
            context.getString(R.string.k),
            context.getString(R.string.m),
            context.getString(R.string.b),
            context.getString(R.string.p)
        )
    val digitGroups =
        (log10(this.toDouble()) / log10(1000.0)).toInt()
    val df = DecimalFormat("#,##0.#")
    df.roundingMode = RoundingMode.DOWN
    df.format(this / 1000.0.pow(digitGroups.toDouble()))
    return df.format(this / 1000.0.pow(digitGroups.toDouble())) + "" + units[digitGroups]
}