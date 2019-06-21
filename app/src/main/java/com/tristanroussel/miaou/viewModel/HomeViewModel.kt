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

    private var page: Int = 0
    private var breedsFullLoaded: Boolean = false

    fun getBreeds() {
        if (!breedsFullLoaded) {
            isLoading.postValue(true)
            CatService.getBreeds(page) { body, success ->
                isLoading.postValue(false)
                if (success && body is List<*>) {
                    when {
                        body.isEmpty() -> breedsFullLoaded = true
                        else -> {
                            body.asListOfType<Breed>()?.let { breeds.postValue(it) }
                            page += 1
                        }
                    }
                }
            }
        }
    }

    fun isLoading(): Boolean = isLoading.value ?: false
}