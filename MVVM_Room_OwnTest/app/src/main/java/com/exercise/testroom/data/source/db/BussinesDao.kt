package com.exercise.testroom.data.source.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BussinesDao {

    @Query("SELECT * FROM businesses")
    suspend fun getAll(): List<Bussines>

    // Other way to do an upsert, the previous way lets say
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(bussines: Bussines)

    @Query("DELETE FROM businesses WHERE id = :bussinesId")
    suspend fun delete(bussinesId: Int)
}
