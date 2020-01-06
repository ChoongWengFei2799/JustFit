package com.example.justfit.ui.all

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.justfit.Exercise
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class AllViewModel : ViewModel(){

    var rootRef = FirebaseDatabase.getInstance().reference

    private val _count = MutableLiveData<Int>()
    val count: LiveData<Int>
        get() = _count

    private val _text = MutableLiveData<String>()
    val text: LiveData<String>
        get() = _text

    init {
        _count.value = 0
        _text.value = ""
    }

    fun checkOld(id: String){
        rootRef.child("Exercise").orderByChild("userId").equalTo(id).addListenerForSingleValueEvent(object :
            ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val children = dataSnapshot.children

                children.forEach{
                    _count.value = (count.value)?.plus(1)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
            }
        })
    }

    fun changeOld(oid: String, nid:String){
        rootRef.child("Exercise").orderByChild("userId").equalTo(oid).addListenerForSingleValueEvent(object :
            ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val children = dataSnapshot.children
                children.forEach{
                    val key = it.key
                    if (key != null) {
                        rootRef.child("Exercise").child(key).child("userId").setValue(nid)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
            }
        })
    }

    fun show(id:String){
        rootRef.child("Exercise").orderByChild("userId").equalTo(id).addListenerForSingleValueEvent(object :
            ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val children = dataSnapshot.children
                children.forEach{
                        val exercise = it.getValue(Exercise::class.java)
                        _text.value = (text.value).plus("Exercise: ".plus(exercise!!.exerciseName).plus("\n"))
                                                  .plus("Date: ".plus(exercise!!.exerciseTime).plus("\n"))
                                                  .plus("Calories: ".plus(exercise!!.calories).plus("\n\n"))
                    }
                }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
            }
        })
    }

    fun clear(id:String){
        rootRef.child("Exercise").orderByChild("userId").equalTo(id).addListenerForSingleValueEvent(object :
            ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val children = dataSnapshot.children
                children.forEach{
                    val key = it.key
                    if (key != null) {
                        rootRef.child("Exercise").child(key).removeValue()
                    }
                }
                _text.value = ""
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }
}