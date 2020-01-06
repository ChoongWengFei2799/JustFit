package com.example.justfit.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.justfit.Profile
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ProfileViewModel : ViewModel() {

    private val _userid = MutableLiveData<String>()
    val userid: LiveData<String>
        get() = _userid

    private val _username = MutableLiveData<String>()
    val username: LiveData<String>
        get() = _username

    private val _lastLogin = MutableLiveData<String>()
    val lastLogin: LiveData<String>
        get() = _lastLogin

    var rootRef = FirebaseDatabase.getInstance().reference

    fun setInfo(id: String){
        rootRef.child("Profile").child(id).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if(dataSnapshot.exists()) {
                    val prof = dataSnapshot.getValue(Profile::class.java)

                    _userid.value = dataSnapshot.key
                    _username.value = prof!!.userName
                    _lastLogin.value = prof!!.lastLogin
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
            }
        })
    }
}