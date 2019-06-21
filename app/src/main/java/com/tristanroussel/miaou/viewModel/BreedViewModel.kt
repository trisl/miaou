package com.tristanroussel.miaou.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tristanroussel.miaou.model.BreedImage
import com.tristanroussel.miaou.service.CatService
import com.tristanroussel.miaou.utils.asListOfType
import com.tristanroussel.miaou.utils.init

class BreedViewModel : ViewModel() {

    val images = MutableLiveData<List<BreedImage>>().init(ArrayList())

    fun getBreedImages(breedId: String) {
        CatService.getBreedImages(breedId) { body, success ->
            if (success && body is List<*>) {
                body.asListOfType<BreedImage>()?.let { images.postValue(it) }
            }
        }
    }
}