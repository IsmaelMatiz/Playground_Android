package com.exercise.testroom.data.source.repos

import com.exercise.testroom.data.source.db.Bussines
import com.exercise.testroom.data.source.db.BussinesDao
import com.exercise.testroom.data.source.db.User
import com.exercise.testroom.data.source.db.UserDao
import com.exercise.testroom.data.source.db.UserWithBusinesses

class UsersRepository (private val usersDao: UserDao, private val businessDao: BussinesDao){
    suspend fun getAllUsers(): List<User> {
        return usersDao.getAllUsers()
    }

    suspend fun getAllUsersWithBusinesses(): List<UserWithBusinesses> {
        return usersDao.getUsersWithBusinesses()
    }

    suspend fun insertUser(user: User) {
        usersDao.upsertUser(user)
    }

    suspend fun insertUserWithBusiness(user: User, business: Bussines) {
        val userId = usersDao.upsertUser(user).toInt()
        businessDao.insert(business.copy(userId = userId))
    }

    suspend fun deleteUser(userId: Int) {
        usersDao.deleteUser(userId)
    }

}