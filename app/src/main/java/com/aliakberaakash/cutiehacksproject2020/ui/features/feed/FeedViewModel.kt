package com.aliakberaakash.cutiehacksproject2020.ui.features.feed

import androidx.lifecycle.ViewModel
import com.aliakberaakash.cutiehacksproject2020.data.Repository
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FeedViewModel : ViewModel() {

    private val repository = Repository()

    fun checkCurrentUser(email : String) = repository.checkCurrentUser(email)

    fun getCurrentUser() = repository.getCurrentUser()

    suspend fun onIWantThisClicked(documentId : String) = repository.onIWantThisClicked(documentId)

    suspend fun onCancelClaimClicked(documentId : String) = repository.onCancelClaimClicked(documentId)

}