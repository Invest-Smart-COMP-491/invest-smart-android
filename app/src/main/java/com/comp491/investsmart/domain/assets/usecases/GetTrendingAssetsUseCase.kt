package com.comp491.investsmart.domain.assets.usecases

import com.comp491.investsmart.domain.assets.entities.Asset

interface GetTrendingAssetsUseCase {

    suspend operator fun invoke(): List<Asset>
}