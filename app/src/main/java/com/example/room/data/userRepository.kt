package com.example.room.data

import androidx.lifecycle.LiveData

class userRepository(private val dao: userDAO) {
    val readAllData: LiveData<List<User>> = dao.readAllData()

    suspend fun addUser(user: User){
        dao.addUser(user)
    }
}