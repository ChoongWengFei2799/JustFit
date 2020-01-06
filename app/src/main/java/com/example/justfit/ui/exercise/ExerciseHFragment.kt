package com.example.justfit.ui.exercise


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.justfit.R
import com.example.justfit.databinding.FragmentExerciseHBinding

/**
 * A simple [Fragment] subclass.
 */
class ExerciseHFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentExerciseHBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_exercise_h,
            container,
            false
        )

        binding.start.setOnClickListener { view?.findNavController()?.navigate(R.id.action_exerciseHFragment_to_exerciseHardFragment) }
        binding.back.setOnClickListener{ view?.findNavController()?.navigate(R.id.action_exerciseHFragment_to_navigation_home) }

        return binding.root
    }
}
