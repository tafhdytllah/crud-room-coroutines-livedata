package com.tafh.example_room_database_coroutines_livedata.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.tafh.example_room_database_coroutines_livedata.model.User
import com.tafh.example_room_database_coroutines_livedata.data.local.db.UserDatabase
import com.tafh.example_room_database_coroutines_livedata.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {

    val getAllUser: LiveData<List<User>>
    private val repository: UserRepository

    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        getAllUser = repository.getAllUser
    }

    fun addUser(user: User) = viewModelScope.launch(Dispatchers.IO) {
        repository.addUser(user)
    }

    fun updateUser(user: User) = viewModelScope.launch(Dispatchers.IO) {
        repository.updateUser(user)
    }

    fun deleteUser(user: User) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteUser(user)
    }

    fun deleteAllUser() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAllUser()
    }


}