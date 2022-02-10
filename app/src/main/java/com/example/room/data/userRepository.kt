package com.example.room.data

import androidx.lifecycle.LiveData
import com.example.room.model.User

class userRepository(private val dao: userDAO) {
    val readAllData: LiveData<List<User>> = dao.readAllData()

    suspend fun addUser(user: User){
        dao.addUser(user)
    }
    suspend fun updateUser(user: User){
        dao.updateUser(user)
    }
}