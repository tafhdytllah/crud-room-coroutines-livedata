package com.tafh.example_room_database_coroutines_livedata.repository

import androidx.lifecycle.LiveData
import com.tafh.example_room_database_coroutines_livedata.data.local.dao.UserDao
import com.tafh.example_room_database_coroutines_livedata.model.User

class UserRepository(private val userDao: UserDao) {

    val getAllUser : LiveData<List<User>> = userDao.getAllUser()

    suspend fun addUser(user: User) = userDao.addUser(user)

    suspend fun updateUser(user: User) = userDao.updateUser(user)

    suspend fun deleteUser(user: User) = userDao.deleteUser(user)

    suspend fun deleteAllUser() = userDao.deleteAllUser()
}