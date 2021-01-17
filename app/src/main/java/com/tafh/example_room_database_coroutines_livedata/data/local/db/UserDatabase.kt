package com.tafh.example_room_database_coroutines_livedata.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tafh.example_room_database_coroutines_livedata.data.local.dao.UserDao
import com.tafh.example_room_database_coroutines_livedata.model.User


@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getDatabase(context: Context): UserDatabase {
            val prepInstance = INSTANCE
            if(prepInstance != null) {
                return prepInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "user_db"
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}