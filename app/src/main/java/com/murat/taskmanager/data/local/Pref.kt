package com.murat.taskmanager.data.local

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

class Pref (private val context: Context) {
    private var pref : SharedPreferences = context.getSharedPreferences(PREF_NAME,MODE_PRIVATE)
    fun isBoardingShow() : Boolean{
        return pref.getBoolean(SHOW_BOARDING,false)
    }
    fun saveBoardingShow(isShow: Boolean) {
        pref.edit().putBoolean(SHOW_BOARDING,isShow).apply()
    }



    companion object{
        private const val PREF_NAME = "pref.task"
        private const val SHOW_BOARDING = "boarding"
    }
}





