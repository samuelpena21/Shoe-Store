package com.udacity.shoestore.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe


class ShoesViewModel : ViewModel() {

    private var _shoeAddedEvent = MutableLiveData<Boolean>()
    val shoeAddedEvent: LiveData<Boolean> = _shoeAddedEvent

    private val _shoeList = MutableLiveData(mutableListOf<Shoe>())
    val shoeList: LiveData<MutableList<Shoe>> = _shoeList

    fun addShoe(shoe: Shoe) {
        _shoeList.value?.add(shoe)
        _shoeList.notifyObserver()
        _shoeAddedEvent.value = true
        handleShoeEvent()
    }

    private fun handleShoeEvent() {
        _shoeAddedEvent.value = false
    }

    //Extension to notify the LiveData when the data of the wrapped list is added.
    private fun <T> MutableLiveData<T>.notifyObserver() {
        this.value = this.value
    }
}