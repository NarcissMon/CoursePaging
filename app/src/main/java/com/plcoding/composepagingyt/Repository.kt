package com.plcoding.composepagingyt

import kotlinx.coroutines.delay

class Repository {

    private val remoteDataSource = (1..1000).map {
        ListItem(
            title = "Курс $it",
            description = "Описание курса $it"
        )
    }

    suspend fun getItems(page: Int, pageSize: Int): Result<List<ListItem>> {
        delay(2000L)
        val startingIndex = page * pageSize
        return if(startingIndex + pageSize <= remoteDataSource.size) {
            Result.success(
                remoteDataSource.slice(startingIndex until startingIndex + pageSize)
            )
        } else Result.success(emptyList())
    }
}