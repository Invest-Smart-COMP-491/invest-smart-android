package com.comp491.investsmart.data.news.inject

<<<<<<< HEAD
import com.comp491.investsmart.data.news.repositories.internal.NewsRepositoryImpl
import com.comp491.investsmart.domain.news.repositories.NewsRepository
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
    abstract fun bindNewsRepository(
        newsRepositoryImpl: NewsRepositoryImpl
    ): NewsRepository
}
=======
}
>>>>>>> 54037c51dcf75ddfa340c2d2b8e5f5f4d767db94
