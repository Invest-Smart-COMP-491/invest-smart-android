package com.comp491.investsmart.domain.assets.usecases

import com.comp491.investsmart.domain.assets.entities.Asset

interface GetAssetsWithKeywordUseCase {

    suspend operator fun invoke(keyword: String): List<Asset>
}