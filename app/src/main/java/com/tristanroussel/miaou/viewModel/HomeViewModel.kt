package com.tristanroussel.miaou.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tristanroussel.miaou.model.Breed
import com.tristanroussel.miaou.service.CatService
import com.tristanroussel.miaou.utils.asListOfType
import com.tristanroussel.miaou.utils.init

class HomeViewModel : ViewModel() {

    val isLoading = MutableLiveData<Boolean>().init(true)
    val breeds = MutableLiveData<List<Breed>>().init(ArrayList())

    fun getBreeds() {
        isLoading.postValue(true)
        CatService.getBreeds { body, success ->
            isLoading.postValue(false)
            if (success && body is List<*>) {
                body.asListOfType<Breed>()?.let { breeds.postValue(it) }
            }
        }
    }
}