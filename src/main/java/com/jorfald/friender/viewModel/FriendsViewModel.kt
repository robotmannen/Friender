package com.jorfald.friender.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.RequestQueue
import com.jorfald.friender.Utils
import com.jorfald.friender.model.dataClasses.User
import com.jorfald.friender.model.repository.FriendsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FriendsViewModel(application: Application) : AndroidViewModel(application) {

    private val friendsRepository = FriendsRepository(application)

    val randomUser: MutableLiveData<User> = MutableLiveData()
    var likedFriends: MutableLiveData<List<User>> = MutableLiveData()

    //Fetches user-data from API
    fun fetchRandomUser(queue: RequestQueue) {
        CoroutineScope(Dispatchers.IO).launch {
            friendsRepository.fetchRandomUser(queue) { user ->
                user.imageUrl = Utils.getProfilePictureUrl(user.gender)
                randomUser.postValue(user)
            }
        }
    }

    //Fetches all users from database and updates mutablelivedata
    fun fetchAndUpdateAllUsers() {
        CoroutineScope(Dispatchers.IO).launch {
            likedFriends.postValue(friendsRepository.fetchAllUsers())
        }
    }

    fun insertUser(user: User) {
        CoroutineScope(Dispatchers.IO).launch {
            friendsRepository.insertUser(user)
        }
    }

    fun deleteUser(id: Long) {
        CoroutineScope(Dispatchers.IO).launch {
            friendsRepository.deleteUser(id)
        }
    }
}