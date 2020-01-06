package com.example.justfit.ui.exercise

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.justfit.R
import com.example.justfit.databinding.FragmentExerciseBinding

class ExerciseFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentExerciseBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_exercise,
            container,
            false
        )

        binding.start.setOnClickListener { view?.findNavController()?.navigate(R.id.action_exerciseFragment_to_exerciseStartFragment) }
        binding.back.setOnClickListener{ view?.findNavController()?.navigate(R.id.action_exerciseFragment_to_navigation_home) }

        return binding.root
    }
}
