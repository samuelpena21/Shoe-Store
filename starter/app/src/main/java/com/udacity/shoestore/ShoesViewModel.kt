package com.udacity.shoestore

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe


class ShoesViewModel : ViewModel() {

    private val shoeList = mutableListOf(
        Shoe(name = "Jordan 13", size = 10.5, company = "Jordan", description = ""),
        Shoe(name = "Jordan 13", size = 10.5, company = "Jordan", description = ""),
        Shoe(name = "Jordan 13", size = 10.5, company = "Jordan", description = ""),
        Shoe(name = "Jordan 13", size = 10.5, company = "Jordan", description = ""),
        Shoe(name = "Jordan 13", size = 10.5, company = "Jordan", description = ""),
        Shoe(name = "Jordan 13", size = 10.5, company = "Jordan", description = ""),
    )

    val shoeLiveData = MutableLiveData(shoeList)

    fun add(shoe: Shoe) {
        shoeList.add(shoe)
    }
}