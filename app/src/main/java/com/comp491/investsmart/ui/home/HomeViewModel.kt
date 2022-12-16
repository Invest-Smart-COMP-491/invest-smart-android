package com.comp491.investsmart.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.comp491.investsmart.domain.news.entities.News
import com.comp491.investsmart.domain.news.usecases.GetAllNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeVMState(
    val stockPrices: List<Pair<String, Double>>,
    val news: List<News>,
    val isLoading: Boolean,
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllNewsUseCase: GetAllNewsUseCase,
) : ViewModel() {

    private val vmState = HomeVMState(
        stockPrices = listOf(
            Pair("THY", 3.2),
            Pair("FADE", -2.11),
            Pair("APPLE", 2.1),
            Pair("BSP", 1.4),
            Pair("META", -5.9),
        ),
        news = emptyList(),
        isLoading = true,
    )
    private val _vmState = MutableStateFlow(vmState)
    val uiState: StateFlow<HomeVMState> = _vmState.asStateFlow()

    init {
        viewModelScope.launch {
            // TODO: handle error case, show an error dialog
            val news =  getAllNewsUseCase().data ?: emptyList()

            _vmState.value = HomeVMState(
                news = news,
                stockPrices = listOf(
                    Pair("THY", 3.2),
                    Pair("FADE", -2.11),
                    Pair("APPLE", 2.1),
                    Pair("BSP", 1.4),
                    Pair("META", -5.9),
                ),
                isLoading = false,
            )
        }
    }

    fun onNewsClicked(url: String) {

    /*
        val intentApp = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + newsIndex))
        val intentBrowser = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=dQw4w9WgXcQ"))

        try {
            context.startActivity(intentApp)
        } catch (ex: ActivityNotFoundException) {
            context.startActivity(intentBrowser)
        }*/
    }

    fun onAssetTickerClicked(assetTicker: String) {

    }
}
