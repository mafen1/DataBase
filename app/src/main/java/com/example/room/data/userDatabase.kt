package com.example.room.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.room.model.User

// делаем бд одноэлементным классом
@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class userDatabase() : RoomDatabase() {
    abstract fun userDao(): userDAO

    companion object {
        @Volatile
        //права на это поле становятся видимыми для всех потоков
        private var INSTANCE: userDatabase? = null



        fun getDatabase(context: Context): userDatabase {
            val tempInstance = INSTANCE
            // есть ли экземпляр бд
            if (tempInstance != null) {
                return tempInstance
            }
            // если нет создаем, в блоке все защищено от одновременного выполнения потоками
            kotlin.synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    userDatabase::class.java,
                    "user_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}