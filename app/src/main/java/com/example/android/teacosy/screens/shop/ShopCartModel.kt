package com.example.android.teacosy.screens.shop

import android.util.Log
import androidx.lifecycle.ViewModel

class ShopCartModel : ViewModel(){

    // The current total
    var total  = 0.0F;
    var fee = 0;

    init {
        Log.i("ShopCartModel", "ShopCartModel created!")
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("TeaViewModel", "TeaViewModel destroyed!")
    }
}