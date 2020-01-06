package com.example.justfit.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.justfit.Exercise
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class HomeViewModel : ViewModel() {

    private val _count = MutableLiveData<Int>()
    val count: LiveData<Int>
        get() = _count

    private val _cal = MutableLiveData<Int>()
    val cal: LiveData<Int>
        get() = _cal

    var rootRef = FirebaseDatabase.getInstance().reference

    init{
        _count.value = 0
        _cal.value = 0
    }

    fun setInfo(id: String){
        rootRef.child("Exercise").orderByChild("userId").equalTo(id).addListenerForSingleValueEvent(object :
            ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val children = dataSnapshot.children
                children.forEach{
                    val exercise = it.getValue(Exercise::class.java)

                    _count.value = (count.value)?.plus(1)
                    _cal.value = (cal.value)?.plus(exercise!!.calories!!)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
            }
        })
    }
}