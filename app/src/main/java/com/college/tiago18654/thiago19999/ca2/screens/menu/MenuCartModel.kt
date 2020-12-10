package com.college.tiago18654.thiago19999.ca2.screens.menu

import android.util.Log
import androidx.lifecycle.ViewModel

class MenuCartModel(type: Int) : ViewModel(){

    // The current total
    var total  = 0.0F;
    var fee = 2;
    var type = type;

    init {
        Log.i("MenuCartModel", "MenuCartModel created!")
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("MenuViewModel", "MenuViewModel destroyed!")
    }
    /**
     * Criar uma função q some cada valor do menu de maneira diferenciada
     * Criar uma função q some os soft drinks
     */
    fun soma(a1: Float, b1: Float){
        total = a1 + b1;
    }
}