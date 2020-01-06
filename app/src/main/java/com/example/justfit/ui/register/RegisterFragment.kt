package com.example.justfit.ui.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.justfit.Profile
import com.example.justfit.R
import com.example.justfit.databinding.FragmentRegisterBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import android.content.Context.INPUT_METHOD_SERVICE
import android.view.inputmethod.InputMethodManager


class RegisterFragment: Fragment() {

    var rootRef = FirebaseDatabase.getInstance().reference

    var database = FirebaseDatabase.getInstance()
    var myRef = database.getReference("Profile")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding: FragmentRegisterBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_register,
            container,
            false
        )

        binding.Reset.setOnClickListener{
            binding.RegisterID.setText("")
            binding.Name.setText("")
            binding.Rpass.setText("")
        }

        binding.submit.setOnClickListener {
            val id = binding.RegisterID.text.toString()
            val name = binding.Name.text.toString()
            val pass = binding.Rpass.text.toString()

            val time = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            val formatted = time.format(formatter)

            if (id == "" || name == "" || pass == "") {
                Toast.makeText(activity, "Invalid data Filled In", Toast.LENGTH_LONG).show()
            }

            else {
                val newProf = Profile(name, pass, formatted)
                readProfile(id, newProf)
            }
        }

        binding.back.setOnClickListener{ view?.findNavController()?.navigate(R.id.action_register_Fragment_to_navigation_profile) }

        return binding.root
    }

    fun saveProfile(id: String, profile: Profile){
        myRef.child(id).setValue(profile)

        val imm = activity!!.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view!!.windowToken, 0)

        val snack = Snackbar.make(activity!!.findViewById(android.R.id.content),
            "Registered Success", Snackbar.LENGTH_LONG)
        snack.setAction("Login Here"
        ) {
            view?.findNavController()?.navigate(R.id.action_register_Fragment_to_login_Fragment)
        }
        snack.show()
    }

    private fun readProfile(id: String, profile: Profile){

        rootRef.child("Profile").child(id).addListenerForSingleValueEvent(object :
            ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if(!dataSnapshot.exists())
                    saveProfile(id, profile)
                else {
                    Toast.makeText(activity, "User Exist", Toast.LENGTH_LONG).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
            }
        })
    }
}