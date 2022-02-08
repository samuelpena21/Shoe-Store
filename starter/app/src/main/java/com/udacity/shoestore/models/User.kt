package com.udacity.shoestore.models

data class User(
    val email: String,
    val password: String,
    val hasTakenOnboarding: Boolean = false
)