package com.aliakberaakash.cutiehacksproject2020.ui.features.post_details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aliakberaakash.cutiehacksproject2020.data.Repository
import com.aliakberaakash.cutiehacksproject2020.data.model.Post
import com.aliakberaakash.cutiehacksproject2020.data.model.User
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class PostDetailsViewModel : ViewModel() {
    private val db = Firebase.firestore
    val post : MutableLiveData<Post> = MutableLiveData()
    val users : MutableLiveData<List<User>> = MutableLiveData()
    val winner = MutableLiveData<Boolean>()
    private val repository = Repository()

    fun checkCurrentUser(email : String) = repository.checkCurrentUser(email)

    fun getCurrentUser() = repository.getCurrentUser()

    suspend fun onIWantThisClicked(documentId : String) = repository.onIWantThisClicked(documentId)

    suspend fun onCancelClaimClicked(documentId : String) = repository.onCancelClaimClicked(documentId)

    fun getPost(documentId : String){
        db.collection("posts").document(documentId)
            .get().addOnCompleteListener{
                if(it.isSuccessful){
                    val postObj = it.result?.toObject(Post::class.java)
                    post.value = postObj
                    val userList = postObj?.claimers
                    getUsers(userList)
                }
            }
    }

    private fun getUsers(usersList : List<String>?){
        if(usersList.isNullOrEmpty())
            return

        db.collection("users")
                .whereIn("email", usersList)
                .get()
                .addOnCompleteListener{
                    if(it.isSuccessful){
                        users.value = it.result?.toObjects(User::class.java)
                    }
                }

    }

    fun setWinner(post: Post) {
        db.collection("posts")
            .document(post.id)
            .update("winner", post.winner)
            .addOnCompleteListener{
                winner.value = true
            }
    }
}