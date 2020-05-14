package com.example.myapplication.controller

import android.content.Context
import androidx.core.content.edit

class Settings(context: Context) {
    val PREFERENCE_NAME = "BitHub"
    val SEARCH_LOCATION_KEY = "SearchLocation"

    val preference = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)

    //GETTER functions
    fun getSearchLocationSetting() : String? {
        return preference.getString(SEARCH_LOCATION_KEY, "Fullerton")
    }

    //SETTER functions (USES KTX EXTENSIONS)
    fun setSearchLocationSetting(input: String){
        preference.edit { putString(SEARCH_LOCATION_KEY, input) }
    }

}