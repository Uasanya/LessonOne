package com.example.lessonone.element

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable


@Parcelize
data class Element(

    val id : Int,
    val name : String,
    val description: String
) : Parcelable
