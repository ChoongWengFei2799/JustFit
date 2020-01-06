package com.example.justfit.ui.login

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
import com.example.justfit.databinding.FragmentLoginBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class LoginFragment : Fragment(){

    var rootRef = FirebaseDatabase.getInstance().reference

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding: FragmentLoginBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_login,
            container,
            false
        )

        binding.login.setOnClickListener{
            val id = binding.loginId.text.toString()
            val pass = binding.password.text.toString()

            if (id == "" || pass == "") {
                Toast.makeText(activity, "Invalid Input", Toast.LENGTH_LONG).show()
            }

            else {
                findProfile(id, pass)
            }
        }

        binding.button2.setOnClickListener { view?.findNavController()?.navigate(R.id.action_login_Fragment_to_navigation_profile) }

        return binding.root
    }

    private fun findProfile(id: String, pass: String){

        rootRef.child("Profile").child(id).addListenerForSingleValueEvent(object :
            ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if(dataSnapshot.exists()){
                    val prof = dataSnapshot.getValue(Profile::class.java)

                    if(prof!!.Password == pass){

                        val time = LocalDateTime.now()
                        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                        val formatted = time.format(formatter)

                        rootRef.child("Profile").child(id).child("lastLogin").setValue(formatted)

                        var prefs = activity!!.getSharedPreferences("INFO", Context.MODE_PRIVATE)
                        val editor = prefs.edit()
                        editor.putString("OldId", prefs.getString("Id",""))
                        editor.apply()

                        editor.putString("Id", dataSnapshot.key)
                        editor.apply()

                        val imm =
                            activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                        imm.hideSoftInputFromWindow(view!!.windowToken, 0)

                        val snack = Snackbar.make(
                            activity!!.findViewById(android.R.id.content),
                            "Login Success", Snackbar.LENGTH_LONG
                        )
                        snack.setAction(
                            "Back"
                        ) {
                            view?.findNavController()
                                ?.navigate(R.id.action_login_Fragment_to_navigation_profile)
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

