package com.example.myapplication.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PageViewModel : ViewModel() {

    private val _index = MutableLiveData<Int>()
    val text: LiveData<String> = Transformations.map(_index) {
        // use a switch case
        when (it.toInt()){
            0 -> UsersFragment()
            1 -> ContributionsFragment()
            2 -> SettingsFragment()
            else -> Log.d("PageViewModel", "Unable to find correct fragment in PageViewModel : ViewModel()")
        }
        "Hello world from section: $it"
    }

    fun setIndex(index: Int) {
        _index.value = index
    }
}