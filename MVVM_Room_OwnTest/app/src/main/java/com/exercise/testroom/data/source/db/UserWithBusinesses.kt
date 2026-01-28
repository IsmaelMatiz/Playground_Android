package com.exercise.testroom.data.source.db

import androidx.room.Embedded
import androidx.room.Relation

data class UserWithBusinesses(
    @Embedded val user: User,
    @Relation( parentColumn = "id", entityColumn = "userId" )
    val businesses: List<Bussines> )