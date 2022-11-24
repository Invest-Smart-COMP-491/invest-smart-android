package com.comp491.investsmart.domain.news.inject

<<<<<<< HEAD
import com.comp491.investsmart.domain.news.usecases.GetAllNewsUseCase
import com.comp491.investsmart.domain.news.usecases.GetAssetNewsUseCase
import com.comp491.investsmart.domain.news.usecases.internal.GetAllNewsUseCaseImpl
import com.comp491.investsmart.domain.news.usecases.internal.GetAssetNewsUseCaseImpl
import dagger.Binds
=======
>>>>>>> 54037c51dcf75ddfa340c2d2b8e5f5f4d767db94
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class HiltNewsModule {
<<<<<<< HEAD

    @Binds
    abstract fun bindGetAllNewsUseCase(
        getAllNewsUseCaseImpl: GetAllNewsUseCaseImpl
    ): GetAllNewsUseCase

    @Binds
    abstract fun bindGetAssetNewsUseCase(
        getAssetNewsUseCaseImpl: GetAssetNewsUseCaseImpl
    ): GetAssetNewsUseCase
}
=======
}
>>>>>>> 54037c51dcf75ddfa340c2d2b8e5f5f4d767db94
