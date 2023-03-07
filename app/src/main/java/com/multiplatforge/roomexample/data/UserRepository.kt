package com.multiplatforge.roomexample.data

import kotlinx.coroutines.flow.Flow

class UserRepository(
    private val userDao: UserDao
) {
    fun getAll(): Flow<List<User>> = userDao.getAll()

    fun getById(userId: Long): User = userDao.getById(userId)

    suspend fun insert(user: User) = userDao.insert(user)

    suspend fun update(user: User) = userDao.update(user)

    suspend fun delete(user: User) = userDao.delete(user)
}