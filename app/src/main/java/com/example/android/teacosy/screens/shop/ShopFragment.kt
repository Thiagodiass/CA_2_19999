/*
 * Copyright (C) 2019 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.teacosy.screens.shop

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ArrayAdapter.createFromResource
import android.widget.ListView
import android.widget.Spinner
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.example.android.teacosy.R
import com.example.android.teacosy.databinding.TeaFragmentBinding
import kotlinx.android.synthetic.main.tea_fragment.*

/**
 * @author Tiago Rufino
 * Fragment where the game is played
 */
class ShopFragment : Fragment() {

    private lateinit var binding: TeaFragmentBinding;
    private lateinit var cartModel: ShopCartModel;

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.tea_fragment,
                container,
                false
        )

        Log.i("TeaFragment", "Called ViewModelProvider.get")
        cartModel = ViewModelProvider(this).get(ShopCartModel::class.java)

        //Pay cart button listener
        binding.correctButton.setOnClickListener { payCart() }

        //Creating the adapter for load the array from the string file
        val adapter = context?.let {
            ArrayAdapter.createFromResource(it, R.array.quantity, android.R.layout.simple_spinner_item)}

        //setting the leayout of the spinner
        adapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //loading the spinner
        binding.spinnerComboDarjelling.adapter = adapter
        binding.spinnerComboAssam.adapter = adapter
        binding.spinnerComboLapsang.adapter = adapter
        binding.spinnerComboEarlGrey.adapter = adapter
        binding.spinnerComboIrishBreakfast.adapter = adapter
        return binding.root
    }

    /**
     * @author Tiago Rufino
     * Pay cart method
     * Set the total and sent to the next fragment (Total_fragment)
     */
    private fun payCart() {
        // text test for navigation
        //Toast.makeText(activity, "Go to pay your cart", Toast.LENGTH_SHORT).show()
        val action = ShopFragmentDirections.actionTeaToCart();
        calculateValue();
        action.total = cartModel.total;
        action.fee = cartModel.fee.toFloat();
        NavHostFragment.findNavController(this).navigate(action)
    }

    /**
     * @author Tiago Rufino
     * calculate the order multipling the selected value with the price
     */
    private fun calculateValue(){
        cartModel.total = cartModel.total + (binding.spinnerComboDarjelling.selectedItem.toString().toFloat() * 8.50f);
        cartModel.total = cartModel.total + (binding.spinnerComboAssam.selectedItem.toString().toFloat() * 7.50f);
        cartModel.total = cartModel.total + (binding.spinnerComboLapsang.selectedItem.toString().toFloat() * 9.50f);
        cartModel.total = cartModel.total + (binding.spinnerComboEarlGrey.selectedItem.toString().toFloat() * 3.50f);
        cartModel.total = cartModel.total + (binding.spinnerComboIrishBreakfast.selectedItem.toString().toFloat() * 2.50f);
    }

    private fun feeCalculator(){
        var totalWeight =
                binding.spinnerComboDarjelling.selectedItem.toString().toFloat() +
                binding.spinnerComboAssam.selectedItem.toString().toFloat()  +
                binding.spinnerComboLapsang.selectedItem.toString().toFloat() +
                binding.spinnerComboEarlGrey.selectedItem.toString().toFloat() +
                binding.spinnerComboIrishBreakfast.selectedItem.toString().toFloat();
        if (totalWeight < 5) cartModel.fee = 5;
    }
}

