package com.aliakberaakash.boston_hacks.ui.features.uploadImage

import androidx.lifecycle.ViewModel
import com.aliakberaakash.boston_hacks.data.Repository

class UploadImageViewModel : ViewModel() {

    private val repository = Repository()

    fun getCurrentUser() = repository.getCurrentUser()

}