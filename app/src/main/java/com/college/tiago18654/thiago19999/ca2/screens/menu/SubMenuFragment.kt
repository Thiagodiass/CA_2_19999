package com.college.tiago18654.thiago19999.ca2.screens.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.college.tiago18654.thiago19999.ca2.R
import com.college.tiago18654.thiago19999.ca2.databinding.SubMenuBinding
import kotlinx.android.synthetic.main.sub_menu.*
import kotlinx.android.synthetic.main.total_fragment.view.*

class SubMenuFragment: Fragment() {

    private lateinit var binding: SubMenuBinding
    private lateinit var cartModel: MenuCartModel
    private lateinit var menuModelFactory: MenuViewModelFactory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.sub_menu, container, false)
        menuModelFactory = MenuViewModelFactory(MenuFragmentArgs.fromBundle(requireArguments()).type,
                MenuFragmentArgs.fromBundle(requireArguments()).total,
                MenuFragmentArgs.fromBundle(requireArguments()).tittleSubItem,
                MenuFragmentArgs.fromBundle(requireArguments()).itemSubMenu)
        cartModel = ViewModelProvider(this, menuModelFactory).get(MenuCartModel::class.java)

        binding.nameFoodText.text = cartModel.tittleSubItem;
        binding.descritionText.text = cartModel.itemSubMenu;

        binding.addToTbeBasketButtonId.setOnClickListener { addToTheCard() }
        binding.backButton.setOnClickListener { backButton() }

        return binding.root
    }

    /**
     * Add button just
     */
    private fun addToTheCard() {
        addItem()
        val action = SubMenuFragmentDirections.actionSubToMenu()
        action.total = cartModel.total
        NavHostFragment.findNavController(this).navigate(action)
    }

    /**
     * Add item into the total
     */
    private fun addItem() {
        var checkBoxItem = 0.0F
        if(binding.prawnsCheckBox.isChecked){
            checkBoxItem += 1F
        }
        if(binding.cocacolaCheckbox.isChecked){
            checkBoxItem += 1.5F
        }
        if(binding.dietcocacolaCheckbox.isChecked){
            checkBoxItem += 1.5F
        }
        if(binding.cluborangeCheckbox.isChecked){
            checkBoxItem += 1.5F
        }
        if(binding.spriteCheckbox.isChecked){
            checkBoxItem += 1.5F
        }
        if(binding.sevenupCheckbox.isChecked){
            checkBoxItem += 1.5F
        }
        cartModel.total = cartModel.total + checkBoxItem +6.00F
    }

    /**
     * Back to the menu screen
     */
    private fun backButton(){
        val action = SubMenuFragmentDirections.actionSubToMenu()
        action.total = cartModel.total
        NavHostFragment.findNavController(this).navigate(action)
    }
}