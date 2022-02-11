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

    private val _shoeCount = MutableLiveData(0)
    val shoeCount: LiveData<Int>
        get() = _shoeCount

    private val _shoe = MutableLiveData<Shoe>(Shoe("", 0.0, "", ""))
    val shoe: LiveData<Shoe>
        get() = _shoe

    fun addShoe(shoe: Shoe?) {
        shoe?.let {
            _shoeList.value?.add(it)
            _shoeList.notifyObserver()
            _shoeCount.value = _shoeList.value?.size ?: 0
            _shoeAddedEvent.value = true
            handleShoeEvent()
        }
    }

    private fun handleShoeEvent() {
        _shoeAddedEvent.value = false
    }

    //Extension to notify the LiveData when the data of the wrapped list is added.
    private fun <T> MutableLiveData<T>.notifyObserver() {
        this.value = this.value
    }
}