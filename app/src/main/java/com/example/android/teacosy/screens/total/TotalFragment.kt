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

package com.example.android.teacosy.screens.total

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.example.android.teacosy.R
import com.example.android.teacosy.databinding.TotalFragmentBinding
import kotlin.system.exitProcess

/**
 * Fragment where the final score is shown, after the game is over
 */
class TotalFragment : Fragment() {

    private lateinit var scoreModel: TotalViewModel
    private lateinit var scoreModelFactory: TotalViewModelFactory

    private var total :Float = 0.0F;
    private var fee : Int = 0;
    private var delivery = 0;

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        scoreModelFactory = TotalViewModelFactory(TotalFragmentArgs.fromBundle(requireArguments()).total,
                TotalFragmentArgs.fromBundle(requireArguments()).fee)
        scoreModel = ViewModelProvider(this, scoreModelFactory).get(TotalViewModel::class.java)
        // Inflate view and obtain an instance of the binding class.
        val binding: TotalFragmentBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.total_fragment,
                container,
                false
        )

        total = scoreModel.total;
        delivery = scoreModel.fee.toInt();
        binding.totalText.text = total.toString()
        binding.deliveryFees.text = delivery.toString();
        binding.total.text = (total + delivery).toString();
        binding.playAgainButton.setOnClickListener { playAgain() }
        binding.exitButton.setOnClickListener { exitProcess(0) }

        return binding.root
    }

    /**
     * @author Tiago Rufino
     * Method to call the tea_fragment again
     */
    private fun playAgain(){
        //text test fof the navigation
        //Toast.makeText(activity, "Back to the main screen", Toast.LENGTH_SHORT).show()
        val action = TotalFragmentDirections.actionTotalDestinationToTeaDestination2()
        NavHostFragment.findNavController(this).navigate(action)
    }
}
