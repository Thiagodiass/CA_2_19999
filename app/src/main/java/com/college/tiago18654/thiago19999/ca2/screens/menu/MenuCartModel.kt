package com.college.tiago18654.thiago19999.ca2.screens.menu

import android.util.Log
import androidx.lifecycle.ViewModel

class MenuCartModel : ViewModel(){


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