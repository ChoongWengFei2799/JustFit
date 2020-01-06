package com.example.justfit.ui.food

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.justfit.R
import com.example.justfit.databinding.FragmentFoodBinding
import android.content.Intent
import android.net.Uri

class FoodFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentFoodBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_food,
            container,
            false
        )

        binding.food1.setOnClickListener { view?.findNavController()?.navigate(R.id.action_navigation_food_to_recipe1Fragment) }
        binding.food2.setOnClickListener { view?.findNavController()?.navigate(R.id.action_navigation_food_to_recipe2Fragment) }

        binding.find.setOnClickListener { findgps() }
        return binding.root
    }

    private fun findgps(){
        val gmmIntentUri = Uri.parse("geo:0,0?q=grocery stores")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        startActivity(mapIntent)
    }
}