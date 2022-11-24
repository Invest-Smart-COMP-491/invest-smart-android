package com.comp491.investsmart.domain.news.usecases.internal

import com.comp491.investsmart.domain.news.entities.News
import com.comp491.investsmart.domain.news.repositories.NewsRepository
import com.comp491.investsmart.domain.news.usecases.GetAllNewsUseCase
import javax.inject.Inject

class GetAllNewsUseCaseImpl @Inject constructor(
    private val newsRepository: NewsRepository,
): GetAllNewsUseCase {

    override suspend fun invoke(): List<News> {
        return newsRepository.getAllNews()
    }
}
