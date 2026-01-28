package com.exercise.testroom.data.source.repos

import com.exercise.testroom.data.source.db.User
import com.exercise.testroom.data.source.db.UserDao
import com.exercise.testroom.data.source.db.UserWithBusinesses

class UsersRepository (private val usersDao: UserDao){
    suspend fun getAllUsers(): List<User> {
        return usersDao.getAllUsers()
    }

    suspend fun getAllUsersWithBusinesses(): List<UserWithBusinesses> {
        return usersDao.getUsersWithBusinesses()
    }

    suspend fun insertUser(user: User) {
        usersDao.upsertUser(user)
    }

    suspend fun deleteUser(userId: Int) {
        usersDao.deleteUser(userId)
    }

}