package com.example.lessonone.element

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Element(

    val id: Int,
    val name: String,
    val description: String
) : Parcelable
