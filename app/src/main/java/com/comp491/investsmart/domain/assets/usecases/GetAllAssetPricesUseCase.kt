package com.comp491.investsmart.domain.assets.usecases

import com.comp491.investsmart.domain.assets.entities.AssetPrice

interface GetAllAssetPricesUseCase {

    suspend operator fun invoke(): List<AssetPrice>
}
