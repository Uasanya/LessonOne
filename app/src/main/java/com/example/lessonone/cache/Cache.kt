package com.example.lessonone.cache

import android.content.Context
import android.content.SharedPreferences
import com.example.lessonone.element.Element
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class Cache(context: Context) {

    companion object{
        private const val PREF : String = "PREF_FILE"
        private const val PREF_KEY_ELEM = "PREF_KEY_ELEM"
    }

    private val preferences: SharedPreferences = context.getSharedPreferences(PREF, Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = preferences.edit()
    private val gson: Gson = Gson()

    fun putElements(element: Element){
        val elem: String = gson.toJson(element)
        editor.putString(PREF_KEY_ELEM, elem)
        editor.apply()
    }

    fun getElement(): Element{
        val elem : String? = preferences.getString(PREF_KEY_ELEM, "")
        val type: Type = object : TypeToken<Element>() {}.getType()
        return gson.fromJson(elem, type)
    }
}