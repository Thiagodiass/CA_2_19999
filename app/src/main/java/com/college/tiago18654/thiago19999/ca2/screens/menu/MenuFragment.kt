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

package com.college.tiago18654.thiago19999.ca2.screens.menu

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.college.tiago18654.thiago19999.ca2.R
import com.college.tiago18654.thiago19999.ca2.databinding.MenuFragmentBinding
import com.college.tiago18654.thiago19999.ca2.screens.inicial.InicialFragmentDirections
import com.college.tiago18654.thiago19999.ca2.screens.total.TotalFragmentDirections
import kotlinx.android.synthetic.main.inicial_fragment.*
import kotlinx.android.synthetic.main.sub_menu.*
import kotlinx.android.synthetic.main.sub_menu.view.*
import kotlinx.android.synthetic.main.total_fragment.*


/**
 * @author Tiago Rufino
 * Fragment where the game is played
 */
class MenuFragment : Fragment() {

    private lateinit var binding: MenuFragmentBinding
    private lateinit var cartModel: MenuCartModel
    private lateinit var listView: ListView
    private lateinit var menuModelFactory: MenuViewModelFactory

    private val total: Float = 6.50F

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.menu_fragment, container,false)
        menuModelFactory = MenuViewModelFactory(MenuFragmentArgs.fromBundle(requireArguments()).type,
                MenuFragmentArgs.fromBundle(requireArguments()).total,
                MenuFragmentArgs.fromBundle(requireArguments()).tittleSubItem,
                MenuFragmentArgs.fromBundle(requireArguments()).itemSubMenu)
        cartModel = ViewModelProvider(this, menuModelFactory).get(MenuCartModel::class.java)


        changeTitle()

        binding.checkoutButton.setOnClickListener { goCheckOut() }

        val menuArrays = resources.getStringArray(R.array.Foods)
        val arrayAdapter = context?.let {
            ArrayAdapter<String>(it, R.layout.row, menuArrays)}

        listView = binding.listaId
        listView.adapter = arrayAdapter

        listView.onItemClickListener = object : AdapterView.OnItemClickListener {
            override fun onItemClick(parent: AdapterView<*>, view: View,
                                     position: Int, id: Long) {
                actionSubMenu(position)
            }
        }

        return binding.root
    }

    /**
     * This method does the action for the submenu
     * Change the item on the submenu passing the information through the facade
     */
    private fun actionSubMenu(position: Int) {
        cartModel.tittleSubItem = resources.getStringArray(R.array.FoodNames)[position]
        cartModel.itemSubMenu = resources.getStringArray(R.array.Foods)[position]
        val action = MenuFragmentDirections.actionMenuToSub()
        action.tittleSubItem = cartModel.tittleSubItem
        action.itemSubMenu = cartModel.itemSubMenu
        action.total = cartModel.total
        NavHostFragment.findNavController(this).navigate(action)
    }

    /**
     * Go to the cart
     * Define if the meny is for buyer of just check
     */
    fun goCheckOut(){
        if( binding.checkoutButton.text.equals("Go Back"))
        {
            val action = MenuFragmentDirections.actionMenuToInicial()
            NavHostFragment.findNavController(this).navigate(action)
        }
        else
        {
            val action = MenuFragmentDirections.actionMenuToCart()
            action.fee = cartModel.fee.toFloat()
            action.total = cartModel.total
            NavHostFragment.findNavController(this).navigate(action)
        }
    }


    fun goBack() {
        val action = MenuFragmentDirections.actionMenuToInicial()
        NavHostFragment.findNavController(this).navigate(action)
    }

    /**
     * Change the type of the buyer - Delivery, collect or just check
     */
    fun changeTitle(){
        if (cartModel.type == 0){
            binding.deliveryFromText.text = getString(R.string.collection_from)
            binding.motoImage.setImageResource(R.drawable.bag)
            cartModel.fee=0
        } else if (cartModel.type == 1){
            binding.deliveryFromText.text = getString(R.string.delivery_from)
            binding.motoImage.setImageResource(R.drawable.moto)
        } else {
            binding.deliveryFromText.text = getString(R.string.menu)
            binding.motoImage.visibility = View.GONE
            binding.checkoutButton.text = "Go Back"
        }
    }
}