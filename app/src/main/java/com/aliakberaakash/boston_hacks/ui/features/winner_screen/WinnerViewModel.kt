package com.aliakberaakash.boston_hacks.ui.features.winner_screen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aliakberaakash.boston_hacks.data.Repository
import com.aliakberaakash.boston_hacks.data.model.Post
import com.aliakberaakash.boston_hacks.data.model.User
import com.google.firebase.firestore.ktx.toObject

class WinnerViewModel : ViewModel() {
    private val repository = Repository()

    val post = MutableLiveData<Post>()
    val user = MutableLiveData<User>()

    suspend fun getPost(documentId : String){
        val snapShot = repository.getPost(documentId)?.toObject<Post>()
        post.postValue(snapShot)
        getUser(snapShot?.winner ?: "")
    }

    private suspend fun getUser(userId : String){
        user.postValue(repository.getUser(userId)?.toObject<User>())
    }

    fun checkUser(email: String) = repository.checkCurrentUser(email)
}