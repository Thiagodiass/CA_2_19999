package com.example.android.teacosy.screens.total

import android.util.Log
import androidx.lifecycle.ViewModel

class TotalViewModel(finalTotal: Float, finalFee: Float) : ViewModel() {
    // The final score
    var total = finalTotal
    var fee = finalFee;

    init {
        Log.i("TOtalViewModel", "The total is $finalTotal + $finalFee")
    }
}