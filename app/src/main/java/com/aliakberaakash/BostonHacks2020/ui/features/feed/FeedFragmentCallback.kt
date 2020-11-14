package com.aliakberaakash.BostonHacks2020.ui.features.feed

interface FeedFragmentCallback {
    fun onIWantThisClicked(documentId : String)
    fun onCancelClaimClicked(documentId : String)
    fun checkCurrentUser(email : String) : Boolean
    fun getCurrentUserEmail() : String?
}