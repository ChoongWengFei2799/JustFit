package com.example.justfit.ui.all

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.justfit.Exercise
import com.example.justfit.R
import com.example.justfit.databinding.FragmentAddBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class AddFragment : Fragment() {

    private var database = FirebaseDatabase.getInstance()
    var myRef = database.getReference("Exercise")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentAddBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_add,
            container,
            false
        )

        binding.button3.setOnClickListener {
            val name = binding.editText.text.toString()
            val calo = binding.editText2.text.toString()

            if (name != "" && calo != "") {
                addnewExercise(name, calo.toInt())
            } else
                Toast.makeText(activity, "Invalid Input", Toast.LENGTH_LONG).show()
        }

        binding.button5.setOnClickListener{ view?.findNavController()?.navigate(R.id.action_addFragment_to_navigation_home) }

        return binding.root
    }

    private fun addnewExercise(exercise:String, calory:Int){
        var prefs = activity!!.getSharedPreferences("INFO", Context.MODE_PRIVATE)
        val id = prefs.getString("Id","")

        var key = myRef.push().key

        if (id != null && key != null) {
            val time = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            val formatted = time.format(formatter)

            myRef.child(key).setValue(Exercise(id, exercise, formatted, calory))

            val imm =
                activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view!!.windowToken, 0)

            val snack = Snackbar.make(
                activity!!.findViewById(android.R.id.content),
                "Exercise Added", Snackbar.LENGTH_LONG
            )
            snack.setAction(
                "Back"
            ) {
                view?.findNavController()
                    ?.navigate(R.id.action_addFragment_to_navigation_home)
            }
            snack.show()
        }
    }
}
