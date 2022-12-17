package com.comp491.investsmart.domain.assets.usecases.internal

import com.comp491.investsmart.data.api.Result
import com.comp491.investsmart.domain.assets.entities.Asset
import com.comp491.investsmart.domain.assets.repositories.AssetsRepository
import com.comp491.investsmart.domain.assets.usecases.GetAssetsWithKeywordUseCase
import javax.inject.Inject

class GetAssetsWithKeywordUseCaseImpl @Inject constructor(
    private val assetsRepository: AssetsRepository,
): GetAssetsWithKeywordUseCase {

    override suspend fun invoke(keyword: String): Result<List<Asset>> {
        return assetsRepository.getAssetsWithKeyword(keyword = keyword)
    }
}
