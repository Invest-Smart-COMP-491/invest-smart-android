package com.comp491.investsmart.domain.assets.usecases

import com.comp491.investsmart.data.api.Result
import com.comp491.investsmart.domain.assets.entities.AssetPrice

interface GetAssetPricesUseCase {

    suspend operator fun invoke(assetTicker: String): Result<List<AssetPrice>>
}
