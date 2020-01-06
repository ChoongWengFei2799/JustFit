package com.example.justfit

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
class Exercise(
    var userId: String? = "",
    var exerciseName: String? = "",
    var exerciseTime: String? = "",
    var calories: Int? = 0
)