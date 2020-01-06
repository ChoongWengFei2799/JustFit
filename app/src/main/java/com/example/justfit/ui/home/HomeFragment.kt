package com.example.justfit.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.justfit.R
import com.example.justfit.databinding.FragmentHomeBinding


class HomeFragment : Fragment(){

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate view and obtain an instance of the binding class
        val binding: FragmentHomeBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home,
            container,
            false
        )

        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

        val prefs = activity!!.getSharedPreferences("INFO", Context.MODE_PRIVATE)
        val pid = prefs.getString("Id","NO_USER")

        viewModel.setInfo(pid)

        viewModel.count.observe(this, Observer { count ->
            viewModel.cal.observe(this, Observer { cal ->
                binding.infoButton.text ="Exercise Done : $count\n\nCalories Lost : $cal"
            })
        })

        binding.addButton.setOnClickListener{ view?.findNavController()?.navigate(R.id.action_navigation_home_to_addFragment) }

        binding.infoButton.setOnClickListener{ view?.findNavController()?.navigate(R.id.action_navigation_home_to_allFragment) }

        binding.beginner.setOnClickListener{ view?.findNavController()?.navigate(R.id.action_navigation_home_to_exerciseFragment) }

        binding.expert.setOnClickListener{ view?.findNavController()?.navigate(R.id.action_navigation_home_to_exerciseHFragment) }

        return binding.root
    }
}