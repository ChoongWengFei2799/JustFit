package com.example.justfit.ui.exercise

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ExerciseHardViewModel: ViewModel() {

    private val _ExerciseName = MutableLiveData<String>()
    val ExerciseName: LiveData<String>
        get() = _ExerciseName

    private val _Time = MutableLiveData<String>()
    val Time: LiveData<String>
        get() = _Time

    private val _image = MutableLiveData<Int>()
    val image: LiveData<Int>
        get() = _image

    init{
        _ExerciseName.value = ""
        _Time.value = ""
        _image.value = 99
    }

    fun start(){
        when (_image.value){
            99 ->{
                _image.value = 0
            }

            0 -> {
                _ExerciseName.value = "Jumping Jacks"
                val timer = object: CountDownTimer(40000, 1000) {
                    override fun onTick(millisUntilFinished: Long){
                        _Time.value = (millisUntilFinished / 1000).toString()
                    }
                    override fun onFinish() {
                        _image.value = (image.value)?.plus(1)
                    }
                }
                timer.start()
            }
            1 -> {
                _ExerciseName.value = "Mountain Climber"
                val timer = object: CountDownTimer(60000, 1000) {
                    override fun onTick(millisUntilFinished: Long){
                        _Time.value = (millisUntilFinished / 1000).toString()
                    }
                    override fun onFinish() {
                        _image.value = (image.value)?.plus(1)
                    }
                }
                timer.start()
            }
            2 -> {
                _ExerciseName.value = "Plank"
                val timer = object: CountDownTimer(40000, 1000) {
                    override fun onTick(millisUntilFinished: Long){
                        _Time.value = (millisUntilFinished / 1000).toString()
                    }
                    override fun onFinish() {
                        _image.value = (image.value)?.plus(1)
                    }
                }
                timer.start()
            }
            3 -> {
                _ExerciseName.value = "Leg Raises"
                val timer = object: CountDownTimer(60000, 1000) {
                    override fun onTick(millisUntilFinished: Long){
                        _Time.value = (millisUntilFinished / 1000).toString()
                    }
                    override fun onFinish() {
                        _image.value = (image.value)?.plus(1)
                    }
                }
                timer.start()
            }
            4 -> {
                _ExerciseName.value = "Abdominal Crunches"
                val timer = object: CountDownTimer(60000, 1000) {
                    override fun onTick(millisUntilFinished: Long){
                        _Time.value = (millisUntilFinished / 1000).toString()
                    }
                    override fun onFinish() {
                        _image.value = (image.value)?.plus(1)
                    }
                }
                timer.start()
            }
            5 -> {
                _ExerciseName.value = "Mountain Climber"
                val timer = object: CountDownTimer(60000, 1000) {
                    override fun onTick(millisUntilFinished: Long){
                        _Time.value = (millisUntilFinished / 1000).toString()
                    }
                    override fun onFinish() {
                        _image.value = (image.value)?.plus(1)
                    }
                }
                timer.start()
            }
            6 -> {
                _ExerciseName.value = "Plank"
                val timer = object: CountDownTimer(40000, 1000) {
                    override fun onTick(millisUntilFinished: Long){
                        _Time.value = (millisUntilFinished / 1000).toString()
                    }
                    override fun onFinish() {
                        _image.value = (image.value)?.plus(1)
                    }
                }
                timer.start()
            }
            7 -> {
                _ExerciseName.value = "Abdominal Crunches"
                val timer = object: CountDownTimer(60000, 1000) {
                    override fun onTick(millisUntilFinished: Long){
                        _Time.value = (millisUntilFinished / 1000).toString()
                    }
                    override fun onFinish() {
                        _image.value = (image.value)?.plus(1)
                    }
                }
                timer.start()
            }
            else -> _image.value = 8
        }
    }
}