package com.exercise.testroom.data.source.repos

import com.exercise.testroom.data.source.db.Bussines
import com.exercise.testroom.data.source.db.BussinesDao

class BussinesRepository(private val bussinesDao: BussinesDao) {

    suspend fun getAllBussines(): List<Bussines> {
        return bussinesDao.getAll()
    }

    suspend fun insertBussines(bussines: Bussines) {
        bussinesDao.insert(bussines)
    }

    suspend fun deleteBussines(bussinesId: Int) {
        bussinesDao.delete(bussinesId)
    }
}