package com.chuanzz.githubuserapp.extension

import android.app.Activity
import com.google.android.material.snackbar.Snackbar

fun Activity.snackBar(message: String) {
    Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG).show()
}