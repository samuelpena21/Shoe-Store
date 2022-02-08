package com.udacity.shoestore.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.SharePrefs
import com.udacity.shoestore.models.User

class UserViewModel : ViewModel() {

    private var _user = MutableLiveData<User?>(null)
    val user: LiveData<User?> = _user

    private var _loginSuccessEvent = MutableLiveData(false)
    val loginSuccessEvent: LiveData<Boolean> = _loginSuccessEvent

    private var _errorLoginEvent = MutableLiveData(false)
    val errorLoginEvent: LiveData<Boolean> = _errorLoginEvent


    //TODO: Eliminate those @Context call in viewModels
    fun login(email: String, password: String, context: Context) {
        if (SharePrefs.validateEmailAndPassword(email, password, context)) {
            SharePrefs.logIn(email, password, context)
            _user.value = SharePrefs.getUser(email, context)
            _loginSuccessEvent.value = true
            handleLoginSuccessEvent()
        } else {
            _errorLoginEvent.value = true
            handleLoginErrorEvent()
        }
    }

    fun logOut(context: Context) {
        SharePrefs.logOut(context)
        _user.value = null
    }

    fun completeOnBoarding(context: Context) {
        _user.value?.let { user ->
            SharePrefs.onBoardingComplete(user, context)
        }
    }

    fun validateIfUserIsLogged(context: Context) {
        _user.value = SharePrefs.getLastLoggedSession(context)
    }

    private fun handleLoginSuccessEvent() {
        _loginSuccessEvent.value = false
    }

    private fun handleLoginErrorEvent() {
        _errorLoginEvent.value = false
    }
}