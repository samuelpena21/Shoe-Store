package com.udacity.shoestore

import android.widget.EditText
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseMethod
import com.google.android.material.textfield.TextInputEditText

object Converter {
    @InverseMethod("stringToDouble")
    @JvmStatic
    fun doubleToString(value: Double
    ): String = value.toString()

    @JvmStatic
    fun stringToDouble(value: String) = value.toDouble()
}