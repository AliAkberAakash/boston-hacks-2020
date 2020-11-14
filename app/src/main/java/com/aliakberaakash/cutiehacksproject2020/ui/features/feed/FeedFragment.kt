package com.aliakberaakash.cutiehacksproject2020.ui.features.feed

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.aliakberaakash.cutiehacksproject2020.R
import com.aliakberaakash.cutiehacksproject2020.data.model.Post
import com.aliakberaakash.cutiehacksproject2020.data.model.User
import com.aliakberaakash.cutiehacksproject2020.ui.features.addfriend.SendPicFragment
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.feed_fragment.*
import kotlinx.coroutines.runBlocking

class FeedFragment : Fragment(), FeedFragmentCallback {

    companion object {
        const val TAG = "FeedFragment"
        fun newInstance() = FeedFragment()
    }

    private lateinit var viewModel: FeedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.feed_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FeedViewModel::class.java)
        val user = Firebase.auth.currentUser

        val postList:MutableList<Post> = mutableListOf()
        val db = Firebase.firestore
        db.collection("posts")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val post = document.toObject(Post::class.java)
                    postList.add(post)
                }
                val adapter = PostAdapter(postList, this)
                feed_recyclerview.adapter = adapter
            }

    }

    override fun checkCurrentUser(email: String) = viewModel.checkCurrentUser(email)

    override fun getCurrentUserEmail() = viewModel.getCurrentUser()?.email

    override fun onIWantThisClicked(documentId : String) {
        runBlocking{ viewModel.onIWantThisClicked(documentId) }
    }

    override fun onCancelClaimClicked(documentId: String) {
        runBlocking{ viewModel.onCancelClaimClicked(documentId) }
    }

}