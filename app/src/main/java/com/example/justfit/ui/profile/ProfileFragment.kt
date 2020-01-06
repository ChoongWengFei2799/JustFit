package com.example.justfit.ui.profile

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.justfit.R
import com.example.justfit.databinding.FragmentProfileBinding
import androidx.navigation.findNavController

class ProfileFragment : Fragment() {

    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate view and obtain an instance of the binding class
        val binding: FragmentProfileBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_profile,
            container,
            false
        )

        viewModel = ViewModelProviders.of(this).get(ProfileViewModel::class.java)

        val prefs = activity!!.getSharedPreferences("INFO", MODE_PRIVATE)
        val pid = prefs.getString("OldId","NO_USER")
        val cid = prefs.getString("Id","")

        if(pid.compareTo("NO_USER") != 0){
            binding.guestView.visibility = View.GONE
            binding.loginbutton.visibility = View.GONE
            binding.registerbutton.visibility = View.GONE

            binding.userid.visibility = View.VISIBLE
            binding.username.visibility = View.VISIBLE
            binding.lastlogin.visibility = View.VISIBLE
            binding.changepass.visibility = View.VISIBLE
            binding.button4.visibility = View.VISIBLE

            viewModel.setInfo(cid)

            viewModel.userid.observe(this, Observer { newuserid ->
                binding.userid.text = "User ID: $newuserid"
            })

            viewModel.username.observe(this, Observer { username ->
                binding.username.text = "Username: $username"
            })

            viewModel.lastLogin.observe(this, Observer { lastLogin ->
                binding.lastlogin.text = "Last Login: $lastLogin"
            })
        }
        else{
            binding.guestView.visibility = View.VISIBLE
            binding.loginbutton.visibility = View.VISIBLE
            binding.registerbutton.visibility = View.VISIBLE

            binding.userid.visibility = View.GONE
            binding.username.visibility = View.GONE
            binding.lastlogin.visibility = View.GONE
            binding.changepass.visibility = View.GONE
            binding.button4.visibility = View.GONE
        }

        binding.changepass.setOnClickListener { view?.findNavController()?.navigate(R.id.action_navigation_profile_to_change_Password_Fragment) }

        binding.button4.setOnClickListener{
            val editor = prefs.edit()
            editor.putString("Id", prefs.getString("OldId",""))
            editor.apply()

            editor.putString("OldId", "NO_USER")
            editor.apply()
            view?.findNavController()?.navigate(R.id.action_navigation_profile_to_navigation_home)
        }

        binding.registerbutton.setOnClickListener{ view?.findNavController()?.navigate(R.id.action_navigation_profile_to_registerFragment) }

        binding.loginbutton.setOnClickListener{view?.findNavController()?.navigate(R.id.action_navigation_profile_to_login_Fragment)}

        return binding.root
    }
}