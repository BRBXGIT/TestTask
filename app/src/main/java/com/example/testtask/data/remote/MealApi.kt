package com.example.testtask.data.remote

import retrofit2.Response
import retrofit2.http.GET

interface MealApi {

    @GET("/api/json/v1/1/categories.php")
    suspend fun getCategories(): Response<CategoryList>

    @GET("/api/json/v1/1/search.php?s")
    suspend fun getMeals(): Response<MealList>
}