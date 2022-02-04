package com.udacity.shoestore.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.User

class UserViewModel : ViewModel() {

    private var _user = MutableLiveData<User?>(null)
    val user: LiveData<User?> = _user

    private var _successEvent = MutableLiveData(false)
    val successEvent: LiveData<Boolean> = _successEvent

    fun login(email: String, password: String) {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            _user.value = User(email = email, password = password)
            _successEvent.value = true
            handleSuccessEvent()
        } else {
            _user.value = null
            handleSuccessEvent()
        }
    }

    private fun handleSuccessEvent() {
        _successEvent.value = false
    }
}