package com.example.testtask.data.repository

import com.example.testtask.data.db.CategoryDao
import com.example.testtask.data.db.MealDao
import com.example.testtask.data.db.OfflineCategory
import com.example.testtask.data.db.OfflineMeal
import com.example.testtask.data.remote.CategoryList
import com.example.testtask.data.remote.MealApi
import com.example.testtask.data.remote.MealList
import com.example.testtask.domain.repository.MealRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class MealRepositoryImpl @Inject constructor(
    private val mealApi: MealApi,
    private val mealDao: MealDao,
    private val categoryDao: CategoryDao,
    private val internetConnection: Boolean
): MealRepository {

    //Api functions
    override suspend fun getCategories(): Response<CategoryList> {
        return mealApi.getCategories()
    }

    override suspend fun getMeals(): Response<MealList> {
        return mealApi.getMeals()
    }

    override fun getInternetConnection(): Boolean {
        return internetConnection
    }

    //Local db functions
    override fun getOfflineMealsByCategory(category: String): Flow<List<OfflineMeal>> {
        return mealDao.getMealsByCategory(category)
    }

    override suspend fun upsertMeal(meal: OfflineMeal) {
        mealDao.upsertNewMeal(meal)
    }

    override suspend fun upsertCategories(category: OfflineCategory) {
        categoryDao.upsertAllCategories(category)
    }

    override fun getAllOfflineCategories(): Flow<List<OfflineCategory>> {
        return categoryDao.getAllCategories()
    }
}