package com.example.justfit.ui.profile

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.justfit.Profile
import com.example.justfit.R
import com.example.justfit.databinding.FragmentChangePasswordBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ChangePasswordFragment: Fragment() {

    var rootRef = FirebaseDatabase.getInstance().reference

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate view and obtain an instance of the binding class
        val binding: FragmentChangePasswordBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_change_password,
            container,
            false
        )

        binding.button3.setOnClickListener { updatePass(binding.editText.text.toString(), binding.editText2.text.toString()) }

        binding.button5.setOnClickListener { view?.findNavController()?.navigate(R.id.action_change_Password_Fragment_to_navigation_profile) }

        return binding.root
    }

    private fun updatePass(oldPass:String, newPass:String){

        var prefs = activity!!.getSharedPreferences("INFO", Context.MODE_PRIVATE)
        val id = prefs.getString("Id","")

        rootRef.child("Profile").child(id).addListenerForSingleValueEvent(object :
            ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if(dataSnapshot.exists()){
                    val prof = dataSnapshot.getValue(Profile::class.java)

                    if(prof!!.Password == oldPass){

                        rootRef.child("Profile").child(id).child("password").setValue(newPass)

                        val imm =
                            activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                        imm.hideSoftInputFromWindow(view!!.windowToken, 0)

                        val snack = Snackbar.make(
                            activity!!.findViewById(android.R.id.content),
                            "Password Changed", Snackbar.LENGTH_LONG
                        )
                        snack.setAction(
                            "Log out"
                        ) {
                            val editor = prefs.edit()
                            editor.putString("Id", prefs.getString("OldId", ""))
                            editor.apply()

                            editor.putString("OldId", "NO_USER")
                            editor.apply()

                            view?.findNavController()
                                ?.navigate(R.id.action_change_Password_Fragment_to_navigation_profile)
                        }
                        snack.show()
                    }
                    else{
                        Toast.makeText(activity, "Incorrect Password", Toast.LENGTH_LONG).show()
                    }
                }
                else {
                    Toast.makeText(activity, "User Doesn't Exist", Toast.LENGTH_LONG).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
            }
        })
    }
}