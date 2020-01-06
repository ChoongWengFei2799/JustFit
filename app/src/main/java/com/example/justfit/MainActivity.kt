package com.example.justfit

import android.content.Context
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private var database = FirebaseDatabase.getInstance()
    var myRef = database.getReference("Profile")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (supportActionBar != null)
            supportActionBar?.hide()

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_food, R.id.navigation_profile
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        val sharedPreference = getSharedPreferences("INFO", Context.MODE_PRIVATE)
        val id = sharedPreference.getString("Id","")
        if(id == null || id == ""){

            val nid = myRef.push().key
            if (nid != null) {
                val newProf = Profile("Guest", "Guest", "Guest")
                myRef.child(nid).setValue(newProf)

                val editor = sharedPreference.edit()
                editor.putString("Id", nid)
                editor.apply()

                editor.putString("OldId", "NO_USER")
                editor.apply()
            }
        }
    }
}
