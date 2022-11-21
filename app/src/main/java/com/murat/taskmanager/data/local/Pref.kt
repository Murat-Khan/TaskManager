package com.murat.taskmanager.data.local

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

class Pref(private val context: Context) {
    private var pref: SharedPreferences = context.getSharedPreferences(PREF_NAME, MODE_PRIVATE)


    fun isBoardingShow(): Boolean {
        return pref.getBoolean(SHOW_BOARDING, false)
    }

    fun saveBoardingShow(isShow: Boolean) {
        pref.edit().putBoolean(SHOW_BOARDING, isShow).apply()
    }

    fun saveName(name: String) {
        pref.edit().putString(NAME_PROFILE, name).apply()
    }

    fun getName(): String? {
        return pref.getString(NAME_PROFILE, "")
    }

    fun saveAge(age: String) {
        pref.edit().putString(AGE_PROFILE, age).apply()
    }

    fun getAge(): String? {
        return pref.getString(AGE_PROFILE, "")
    }

    fun saveImage(image: String) {

        pref.edit().putString(IMAGE_PROFILE, image).apply()
    }

    fun getImage(): String? {
        return pref.getString(IMAGE_PROFILE, "")
    }


    companion object {
        private const val PREF_NAME = "pref.task"
        private const val SHOW_BOARDING = "boarding"
        private const val NAME_PROFILE = "name.profile"
        private const val AGE_PROFILE = "age.profile"
        private const val IMAGE_PROFILE = "image.profile"


    }
}





