package com.comp491.investsmart.data.news.repositories.internal

<<<<<<< HEAD
import com.comp491.investsmart.domain.news.entities.News
=======
>>>>>>> 54037c51dcf75ddfa340c2d2b8e5f5f4d767db94
import com.comp491.investsmart.domain.news.repositories.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(

) : NewsRepository {

<<<<<<< HEAD
    override suspend fun getAllNews(): List<News> {
        return emptyList()
    }

    override suspend fun getAssetNews(assetTicker: String): List<News> {
        return emptyList()
    }
}
=======
}
>>>>>>> 54037c51dcf75ddfa340c2d2b8e5f5f4d767db94
