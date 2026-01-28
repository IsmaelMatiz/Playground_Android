package com.exercise.testroom.data.source.db

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert

@Dao
interface UserDao {
    @Transaction
    @Query("SELECT * FROM users")
    fun getUsersWithBusinesses(): List<UserWithBusinesses>

    @Query("SELECT * FROM users")
    fun getAllUsers(): List<User>

    @Upsert
    fun upsertUser(user: User)

    @Query("DELETE FROM users WHERE id = :userId")
    fun deleteUser(userId: Int)

}