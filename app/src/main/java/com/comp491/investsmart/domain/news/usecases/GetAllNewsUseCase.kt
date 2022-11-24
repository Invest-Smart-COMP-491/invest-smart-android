package com.comp491.investsmart.domain.news.usecases

import com.comp491.investsmart.domain.news.entities.News

interface GetAllNewsUseCase {

    suspend operator fun invoke(): List<News>
}
