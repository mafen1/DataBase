package com.example.room.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface userDAO {
    // если добавится точно такой же пользователь, то мы просто проигнорируем
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Query("SELECT * FROM USERTABLE ORDER BY ID ASC")
    fun readAllData(): LiveData<List<User>>
}