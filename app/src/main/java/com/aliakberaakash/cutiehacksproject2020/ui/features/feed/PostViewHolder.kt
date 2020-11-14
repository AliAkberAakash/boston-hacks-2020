package com.aliakberaakash.cutiehacksproject2020.ui.features.feed

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.aliakberaakash.cutiehacksproject2020.R
import com.google.android.material.button.MaterialButton

class PostViewHolder(item : View) : RecyclerView.ViewHolder(item){
    val postContainer : CardView = item.findViewById(R.id.post_container)
    var postImage : ImageView = item.findViewById(R.id.post_image)
    var profileImage : ImageView = item.findViewById(R.id.profile_image)
    var iWantThisButton : MaterialButton = item.findViewById(R.id.i_want_this_button)
    var cancelButton : MaterialButton = item.findViewById(R.id.cancel_claim)
    var userName : TextView = item.findViewById(R.id.user_name)
    var description : TextView = item.findViewById(R.id.description)
}