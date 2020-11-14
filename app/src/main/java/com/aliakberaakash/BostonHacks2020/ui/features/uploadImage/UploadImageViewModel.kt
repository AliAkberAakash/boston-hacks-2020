package com.aliakberaakash.BostonHacks2020.ui.features.uploadImage

import androidx.lifecycle.ViewModel
import com.aliakberaakash.BostonHacks2020.data.Repository

class UploadImageViewModel : ViewModel() {

    private val repository = Repository()

    fun getCurrentUser() = repository.getCurrentUser()

}