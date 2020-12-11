package com.college.tiago18654.thiago19999.ca2.screens.menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MenuViewModelFactory
    (private val type: Int,
     private val total: Float,
     private val tittleSubItem: String,
     private val itemSubMenu: String)
    : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MenuCartModel::class.java)) {
            return MenuCartModel(type, total, tittleSubItem, itemSubMenu) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}