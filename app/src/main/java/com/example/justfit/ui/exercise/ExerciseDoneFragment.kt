package com.example.justfit.ui.exercise

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.justfit.R
import com.example.justfit.databinding.FragmentExerciseDoneBinding

class ExerciseDoneFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentExerciseDoneBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_exercise_done,
            container,
            false
        )

        binding.back.setOnClickListener{ view?.findNavController()?.navigate(R.id.action_exerciseDoneFragment_to_navigation_home) }
        return binding.root
    }
}
