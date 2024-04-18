package com.example.appleproject.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Item(
    val image: Int,
    val title: String,
    val explain: String,
    val address: String,
    val name: String,
    val price: Int,
    val like: String,
    val chat: String
) : Parcelable