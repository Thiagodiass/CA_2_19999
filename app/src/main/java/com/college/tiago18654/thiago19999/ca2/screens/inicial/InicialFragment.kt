package com.college.tiago18654.thiago19999.ca2.screens.inicial

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.college.tiago18654.thiago19999.ca2.R
import com.college.tiago18654.thiago19999.ca2.databinding.InicialFragmentBinding
import com.college.tiago18654.thiago19999.ca2.screens.shop.ShopCartModel
import kotlinx.android.synthetic.main.inicial_fragment.view.*

class InicialFragment : Fragment() {

    private lateinit var binding: InicialFragmentBinding;
    private lateinit var cartModel: ShopCartModel;

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.inicial_fragment,
                container,
                false
        )

        Log.i("InicialFragment", "Inicial page.")

        //Pay cart button listener
        binding.deliveryButton.setOnClickListener { menuCall("collect") }
        binding.collectButton.setOnClickListener { menuCall("delivery") }

        return binding.root

    }

    /**
     * @author Tiago Rufino
     * Call the menu screen
     */
    private fun menuCall(type : String) {
        // text test for navigation
        //Toast.makeText(activity, "Go to pay your cart", Toast.LENGTH_SHORT).show()
        val action = InicialFragmentDirections.actionInicialToTea();

        NavHostFragment.findNavController(this).navigate(action)
    }
}