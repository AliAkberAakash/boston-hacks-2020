package com.aliakberaakash.BostonHacks2020.ui.features.feed

import androidx.lifecycle.ViewModel
import com.aliakberaakash.BostonHacks2020.data.Repository

class FeedViewModel : ViewModel() {

    private val repository = Repository()

    fun checkCurrentUser(email : String) = repository.checkCurrentUser(email)

    fun getCurrentUser() = repository.getCurrentUser()

    suspend fun onIWantThisClicked(documentId : String) = repository.onIWantThisClicked(documentId)

    suspend fun onCancelClaimClicked(documentId : String) = repository.onCancelClaimClicked(documentId)

}