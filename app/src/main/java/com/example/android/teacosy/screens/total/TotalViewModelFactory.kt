package com.example.android.teacosy.screens.total

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class TotalViewModelFactory(private val finalScore: Float, private val finalFee: Float) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TotalViewModel::class.java)) {
            return TotalViewModel(finalScore, finalFee) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}