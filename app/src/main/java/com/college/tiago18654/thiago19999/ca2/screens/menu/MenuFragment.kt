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
import android.os.Bundle
import android.util.Log
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


/**
 * @author Tiago Rufino
 * Fragment where the game is played
 */
class MenuFragment : Fragment() {

    private lateinit var binding: MenuFragmentBinding
    private lateinit var cartModel: MenuCartModel
    private lateinit var listView: ListView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.menu_fragment, container,false)

        val menuArrays = resources.getStringArray(R.array.Foods)
        val arrayAdapter = context?.let {
            ArrayAdapter<String>(it, android.R.layout.simple_list_item_1, menuArrays)}

        listView = binding.listaId
        listView.adapter = arrayAdapter

        listView.onItemClickListener = object : AdapterView.OnItemClickListener {
            override fun onItemClick(parent: AdapterView<*>, view: View,
                                     position: Int, id: Long) {
                // value of item that is clicked
                val itemValue = listView.getItemAtPosition(position) as String
                callMenuPopUp(true)
                // Toast.makeText(context,"Position :$position\nItem Value : $itemValue", Toast.LENGTH_LONG).show()
            }
        }

        return binding.root
    }

    /**
     * Call the popup window
     *
     */
    @SuppressLint("ResourceType")
    fun callMenuPopUp(open: Boolean){
//        val popup = PopupMenu(context, binding.correctButton)
//        popup.inflate(R.layout.sub_menu)
//        popup.setOnMenuItemClickListener {
//            //Toast.makeText(context, "Item: " + it.title, Toast.LENGTH_SHORT).show()
//            true
//        }
//        popup.show()

        val window = PopupWindow(context)
        val view = layoutInflater.inflate(R.layout.sub_menu, null)

        window.contentView = view
        window.showAsDropDown(binding.deliveryFromText)
        val buttonAdd = view.findViewById<Button>(R.id.add_to_tbe_basket_button_id)
        buttonAdd.setOnClickListener {
            window.dismiss()
            // text test for navigation
            Toast.makeText(activity, "Go to pay your cart", Toast.LENGTH_SHORT).show()
            window.dismiss()
            val action = MenuFragmentDirections.actionMenuToCart()
            NavHostFragment.findNavController(this).navigate(action)
        }

    }
}

