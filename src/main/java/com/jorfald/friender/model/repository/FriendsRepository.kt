package com.jorfald.friender.model.repository

import android.app.Application
import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.google.gson.Gson
import com.jorfald.friender.model.dataClasses.User
import com.jorfald.friender.BASE_URL
import com.jorfald.friender.model.database.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FriendsRepository(application: Application) {

    private val database = AppDatabase.getDatabase(application)
    private val userDao = database.userDao()

    fun fetchRandomUser(requestQueue: RequestQueue, callback: (User) -> Unit) {
        val randomUserRequest = StringRequest(
            Request.Method.GET,
            BASE_URL,
            { json ->
                val randomUser = Gson().fromJson(json, User::class.java)
                callback(randomUser)
            },
            { error ->
                Log.e("Error:", error.message ?: "")
            }
        )
        requestQueue.add(randomUserRequest)
    }

    fun fetchAllUsers(): List<User> {
        return userDao.getAllUsers()
    }

    fun insertUser(user: User) {
        userDao.insertUser(user)
    }

    fun deleteUser(id: Long) {
        userDao.deleteUser(id)
    }
}