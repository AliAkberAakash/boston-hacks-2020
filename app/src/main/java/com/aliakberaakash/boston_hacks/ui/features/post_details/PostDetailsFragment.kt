package com.aliakberaakash.boston_hacks.ui.features.post_details

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.navArgs
import com.aliakberaakash.boston_hacks.R
import com.aliakberaakash.boston_hacks.core.makeItGone
import com.aliakberaakash.boston_hacks.data.model.Post
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.post_details_fragment.*
import kotlinx.coroutines.runBlocking
import timber.log.Timber

class PostDetailsFragment : Fragment() {

    private lateinit var viewModel: PostDetailsViewModel
    private lateinit var adapter: PostDetailsAdapter
    private val args: PostDetailsFragmentArgs by navArgs()
    private  lateinit var post : Post

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.post_details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PostDetailsViewModel::class.java)
        viewModel.getPost(args.postId)

        adapter = PostDetailsAdapter(mutableListOf())
        users_list.adapter = adapter

        viewModel.post.observe(viewLifecycleOwner, {

            post = it

            if (viewModel.checkCurrentUser(it.user.email)) {
                if (it.claimers.isNullOrEmpty()) {
                    no_item_message.visibility = View.VISIBLE
                    no_item_message.text = getString(R.string.no_claimers_yet_msg)
                    draw_winner_button.isEnabled = false
                }
                draw_winner_button.text = getString(R.string.draw_winner)
            } else {
                if (it.claimers.isNullOrEmpty())
                    no_item_message.visibility = View.VISIBLE

                if (viewModel.getCurrentUser().email !in it.claimers) {
                    draw_winner_button.text = getString(R.string.i_want_this)
                } else {
                    draw_winner_button.visibility = View.GONE
                }

            }

        })

        viewModel.users.observe(viewLifecycleOwner, {
            adapter.usersList =  it
            adapter.notifyDataSetChanged()
        })

        viewModel.winner.observe(viewLifecycleOwner, {
            if(it){
                val action = PostDetailsFragmentDirections.actionPostDetailsFragmentToWinnerFragment(
                    viewModel.post.value?.id ?: ""
                )
                findNavController(this).navigate(action)
            }
        })

        draw_winner_button.setOnClickListener {

            if(draw_winner_button.text == getString(R.string.draw_winner))
            {
                MaterialAlertDialogBuilder(requireContext())
                    .setTitle(getString(R.string.draw_a_winner))
                    .setMessage(getString(R.string.draw_winner_prompt))
                    .setNeutralButton(resources.getString(R.string.no)) { dialog, _ ->
                        dialog.dismiss()
                    }
                    .setPositiveButton(resources.getString(R.string.accept)) { _, _ ->
                        val winner = (post.claimers.indices-1).random()
                        Timber.d(winner.toString())
                        post.winner = post.claimers[winner]
                        viewModel.setWinner(post)
                    }
                    .show()

            }else{

                runBlocking {
                    viewModel.onIWantThisClicked(args.postId)
                    draw_winner_button.makeItGone()
                    no_item_message.makeItGone()
                    viewModel.getPost(args.postId)
                }


            }
        }

    }



}