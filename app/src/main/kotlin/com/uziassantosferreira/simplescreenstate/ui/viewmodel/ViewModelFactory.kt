package com.uziassantosferreira.simplescreenstate.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class ViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SimpleScreenStateViewModel::class.java)) {
            return SimpleScreenStateViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}