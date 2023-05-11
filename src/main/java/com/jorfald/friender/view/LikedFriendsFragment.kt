package com.jorfald.friender.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jorfald.friender.R
import com.jorfald.friender.view.adapter.FriendsAdapter
import com.jorfald.friender.viewModel.FriendsViewModel


class LikedFriendsFragment : Fragment() {
    private val viewModel: FriendsViewModel by activityViewModels()

    private lateinit var customLayoutManager: LinearLayoutManager
    private lateinit var friendsAdapter: FriendsAdapter

    private lateinit var recyclerView: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_liked_friends, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindRecyclerView(view)
        friendsAdapter()
        populateRecyclerView()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.fetchAndUpdateAllUsers()
    }

    private fun friendsAdapter() {
        customLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        friendsAdapter = FriendsAdapter(emptyList()) { crossOnClickedUser ->
            viewModel.deleteUser(crossOnClickedUser.id)
            viewModel.fetchAndUpdateAllUsers()
        }
        recyclerView.layoutManager = customLayoutManager
        recyclerView.adapter = friendsAdapter
    }

    private fun bindRecyclerView(view: View) {
        recyclerView = view.findViewById(R.id.friends_recycler)
    }

    private fun populateRecyclerView() {
        viewModel.likedFriends.observe(viewLifecycleOwner) {
            friendsAdapter.updateData(it)
        }
    }
}