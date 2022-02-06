package com.udacity.shoestore.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.User

class UserViewModel : ViewModel() {

    private var _user = MutableLiveData<User?>(null)
    val user: LiveData<User?> = _user

    private var _loginSuccessEvent = MutableLiveData(false)
    val loginSuccessEvent: LiveData<Boolean> = _loginSuccessEvent

    private var _logOutSuccessEvent = MutableLiveData(false)
    val logOutSuccessEvent: LiveData<Boolean> = _logOutSuccessEvent

    fun login(email: String, password: String) {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            _user.value = User(email = email, password = password)
            _loginSuccessEvent.value = true
            handleLoginSuccessEvent()
        } else {
            _user.value = null
            handleLoginSuccessEvent()
        }
    }

    fun logOut() {
        _logOutSuccessEvent.value = true
        handleLogOutSuccessEvent()
        _user.value = null
    }

    private fun handleLoginSuccessEvent() {
        _loginSuccessEvent.value = false
    }

    private fun handleLogOutSuccessEvent() {
        _logOutSuccessEvent.value = false
    }
}