package com.example.justfit

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
class Profile(
    var userName: String? = "",
    var Password: String? = "",
    var lastLogin: String? = ""
)