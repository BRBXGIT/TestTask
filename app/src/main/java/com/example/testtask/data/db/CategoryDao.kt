package com.example.testtask.data.db

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {
    @Query("SELECT * FROM offlinecategory")
    fun getAllCategories(): Flow<List<OfflineCategory>>

    @Upsert
    suspend fun upsertAllCategories(category: OfflineCategory)
}