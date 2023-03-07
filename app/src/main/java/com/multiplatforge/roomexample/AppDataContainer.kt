package com.multiplatforge.roomexample

import android.content.Context
import com.multiplatforge.roomexample.data.AppDatabase
import com.multiplatforge.roomexample.data.UserRepository

class AppContainer(private val context: Context) {

    val userRepository: UserRepository by lazy {
        UserRepository(AppDatabase.getInstance(context).userDao())
    }
}