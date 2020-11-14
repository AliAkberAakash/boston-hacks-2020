package com.aliakberaakash.BostonHacks2020.data.model

data class Post (
    var id : String = "",
    var user : User = User(),
    var description : String = "",
    var image : String = "",
    var claimers : List<String> = mutableListOf(),
    var winner : String = ""
)