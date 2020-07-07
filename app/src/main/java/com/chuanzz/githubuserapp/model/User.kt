package com.chuanzz.githubuserapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    var userName: String?,
    var name: String?,
    var avatar: Int?,
    var company: String? = null,
    var location: String? = null,
    var repository: List<String>? = null,
    var followers: Long = 0,
    var following: Long = 0
) : Parcelable