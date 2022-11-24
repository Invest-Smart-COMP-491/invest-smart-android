package com.comp491.investsmart.domain.assets.usecases

import com.comp491.investsmart.domain.assets.entities.AssetPrice

interface GetAssetPricesUseCase {

    suspend operator fun invoke(assetTicker: String): List<AssetPrice>
}
