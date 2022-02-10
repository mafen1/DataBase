package com.example.room.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.room.model.User

@Dao
interface userDAO {
    // если добавится точно такой же пользователь, то мы просто проигнорируем
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Query("SELECT * FROM USERTABLE ORDER BY ID ASC")
    fun readAllData(): LiveData<List<User>>
}