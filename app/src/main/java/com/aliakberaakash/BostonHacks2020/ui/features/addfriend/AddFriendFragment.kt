package com.aliakberaakash.BostonHacks2020.ui.features.addfriend

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.aliakberaakash.BostonHacks2020.R

class AddFriendFragment : Fragment() {

    companion object {
        fun newInstance() = AddFriendFragment()
    }

    private lateinit var viewModel: AddFriendViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view:View = inflater.inflate(R.layout.add_friend_fragment, container, false)
        var connectBtn: Button = view.findViewById(R.id.button)
        connectBtn.setOnClickListener(View.OnClickListener {
            val fragmentManager = activity?.supportFragmentManager;
            if (fragmentManager != null) {
                fragmentManager.beginTransaction()
                    .add(R.id.sendfriend_fragment, SendPicFragment())
                    .commit()
            }
        })
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddFriendViewModel::class.java)
        // TODO: Use the ViewModel
    }

}