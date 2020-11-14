package com.aliakberaakash.cutiehacksproject2020.ui.features.feed

import com.google.firebase.firestore.auth.User

interface FeedFragmentCallback {
    fun onIWantThisClicked(documentId : String)
    fun onCancelClaimClicked(documentId : String)
    fun checkCurrentUser(email : String) : Boolean
    fun getCurrentUserEmail() : String?
}