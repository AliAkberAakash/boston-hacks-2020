package com.aliakberaakash.cutiehacksproject2020.ui.features.post_details

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aliakberaakash.cutiehacksproject2020.R
import com.aliakberaakash.cutiehacksproject2020.data.model.User

class PostDetailsAdapter(var usersList : List<User>) : RecyclerView.Adapter<PostDetailsViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostDetailsViewHolder {
        context = parent.context

        val view = LayoutInflater.from(context).inflate(R.layout.item_single_user, parent, false)

        return PostDetailsViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostDetailsViewHolder, position: Int) {
        holder.userName.text = usersList[position].userName
    }

    override fun getItemCount() = usersList.size
}