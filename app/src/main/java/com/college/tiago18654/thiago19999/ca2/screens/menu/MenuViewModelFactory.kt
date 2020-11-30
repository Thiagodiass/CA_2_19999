package com.college.tiago18654.thiago19999.ca2.screens.menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MenuViewModelFactory (private val type: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MenuCartModel::class.java)) {
            return MenuCartModel(type) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}