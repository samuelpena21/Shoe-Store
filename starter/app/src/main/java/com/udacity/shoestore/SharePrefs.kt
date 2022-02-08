package com.udacity.shoestore

import android.content.Context
import com.google.gson.Gson
import com.udacity.shoestore.models.User

object SharePrefs {

    private const val PREF_NAME = "com.udacity.shoestore.PREFERENCE_USER_FILE_KEY"
    private const val USER_LOGGED_IN_KEY = "user_logged_in_key"
    private const val LAST_LOGGED_IN_EMAIL_KEY = "last_logged_in_email_key"

    fun saveUser(user: User, context: Context) {
        val pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val stringUser = Gson().toJson(user)

        with(pref.edit()) {
            putString(user.email, stringUser)
            apply()
        }
    }

    fun validateEmailAndPassword(email: String, password: String, context: Context): Boolean {
        val pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val userString = pref.getString(email, null)
        if (!userString.isNullOrEmpty()) {
            val gUser = Gson().fromJson(userString, User::class.java)
            return (gUser.password == password)
        }

        return false
    }

    fun logIn(email: String, password: String, context: Context) {
        if (!validateEmailAndPassword(email, password, context)) return
        val pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        with(pref.edit()) {
            putBoolean(USER_LOGGED_IN_KEY, true)
            putString(LAST_LOGGED_IN_EMAIL_KEY, email)
            apply()
        }
    }

    fun getUser(email: String, context: Context): User? {
        val pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val userString = pref.getString(email, null)

        return if (userString == null) null else Gson().fromJson(userString, User::class.java)
    }

    fun logOut(context: Context) {
        val pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        with(pref.edit()) {
            putBoolean(USER_LOGGED_IN_KEY, false)
            putString(LAST_LOGGED_IN_EMAIL_KEY, null)
            apply()
        }
    }

    fun getLastLoggedSession(context: Context): User? {
        val pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val lastLoggedString = pref.getString(LAST_LOGGED_IN_EMAIL_KEY, null)
        val lastLoggedStringValue = pref.getString(lastLoggedString, null)
        return if (lastLoggedStringValue.isNullOrEmpty()) {
            null
        } else {
            return Gson().fromJson(lastLoggedStringValue, User::class.java)
        }
    }

    fun onBoardingComplete(user: User, context: Context) {
        saveUser(user = user.copy(hasTakenOnboarding = true), context)
    }
}