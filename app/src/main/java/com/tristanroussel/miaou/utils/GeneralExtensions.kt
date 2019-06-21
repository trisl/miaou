package com.tristanroussel.miaou.utils

import android.view.View
import androidx.lifecycle.MutableLiveData

@Suppress("UNCHECKED_CAST")
inline fun <reified T> List<*>.asListOfType(): List<T>? =
        if (all { it is T }) this as List<T> else null

fun <T : Any?> MutableLiveData<T>.init(initialValue: T) = apply { postValue(initialValue) }

fun Boolean.changeVisibility(invert: Boolean = false): Int =
        if (this.xor(invert)) View.VISIBLE else View.GONE