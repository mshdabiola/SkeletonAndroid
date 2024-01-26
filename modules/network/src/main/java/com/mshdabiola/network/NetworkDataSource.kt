package com.mshdabiola.network


interface NetworkDataSource {
    suspend fun getRecommendation(): List<String>
}


