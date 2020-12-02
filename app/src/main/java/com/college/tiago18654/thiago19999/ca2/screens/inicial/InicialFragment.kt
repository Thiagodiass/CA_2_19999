package com.college.tiago18654.thiago19999.ca2.screens.inicial

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.college.tiago18654.thiago19999.ca2.R
import com.college.tiago18654.thiago19999.ca2.databinding.InicialFragmentBinding
import com.college.tiago18654.thiago19999.ca2.screens.menu.MenuCartModel


class InicialFragment : Fragment() {

    private lateinit var binding: InicialFragmentBinding;
    private lateinit var cartModel: MenuCartModel;

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.inicial_fragment,
                container,
                false
        )

        Log.i("InicialFragment", "Inicial page.")

        //Pay cart button listener
        binding.deliveryButton.setOnClickListener { menuCall(1) }
        binding.collectButton.setOnClickListener { menuCall(0) }
        binding.menuButton.setOnClickListener { menuCall(2) }

        return binding.root

    }

    /**
     * @author Tiago Rufino
     * Call the menu screen
     */
    private fun menuCall(type : Int) {
        // text test for navigation
        //Toast.makeText(activity, "Go to pay your cart", Toast.LENGTH_SHORT).show()
        Log.i("InicialFragment", "menucall")
        val action = InicialFragmentDirections.actionInicialToTea();
        action.type = type
        NavHostFragment.findNavController(this).navigate(action)
    }
}