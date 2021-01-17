package com.tafh.example_room_database_coroutines_livedata.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.tafh.example_room_database_coroutines_livedata.model.User


@Dao
interface UserDao {

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun getAllUser(): LiveData<List<User>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(vararg user: User)

    @Update
    suspend fun updateUser(vararg user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("DELETE FROM user_table")
    suspend fun deleteAllUser()

}