package com.example.justfit.ui.all

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
import com.example.justfit.databinding.FragmentAllBinding

class AllFragment : Fragment()  {

    private lateinit var viewModel: AllViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate view and obtain an instance of the binding class
        val binding: FragmentAllBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_all,
            container,
            false
        )

        viewModel =
            ViewModelProviders.of(this).get(AllViewModel::class.java)

        var prefs = activity!!.getSharedPreferences("INFO", Context.MODE_PRIVATE)
        var current = prefs.getString("Id", "")
        var prev = prefs.getString("OldId", "")

        if(prev != "NO_USER") {
            viewModel.checkOld(prev)

            viewModel.count.observe(this, Observer { count ->
                if(count>0){
                    binding.reIns.visibility = View.VISIBLE
                    binding.reIns.setOnClickListener { viewModel.changeOld( prev, current) }
                }
                else
                    binding.reIns.visibility = View.GONE
            })
        }
        else
            binding.reIns.visibility = View.GONE

        viewModel.show(current)
        viewModel.text.observe(this, Observer { text ->
            if(text != ""){
                binding.History.text = text
            }
            else
                binding.History.text = "No Exercise Done"
        })

        binding.clear.setOnClickListener{
            viewModel.clear(current)
        }

        binding.button.setOnClickListener{ view?.findNavController()?.navigate(R.id.action_allFragment_to_navigation_home) }

        return binding.root
    }
}
