package com.exercise.testroom.data.source.db

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert

@Dao
interface UserDao {
    @Transaction
    @Query("SELECT * FROM users")
    //Puede q aqui falte el flow ojo= Flow<List<UserWithBusinesses>>
    suspend fun getUsersWithBusinesses(): List<UserWithBusinesses>

    @Query("SELECT * FROM users")
    suspend fun getAllUsers(): List<User>

    @Upsert
    suspend fun upsertUser(user: User): Long

    @Query("DELETE FROM users WHERE id = :userId")
    suspend fun deleteUser(userId: Int)

}