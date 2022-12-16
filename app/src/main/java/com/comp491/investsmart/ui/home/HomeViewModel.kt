package com.comp491.investsmart.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.comp491.investsmart.data.api.retrofit.InvestSmartService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeVMState(
    val stockPrices: List<Pair<String, Double>>,
    val news: List<String>,
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val investSmartService: InvestSmartService,
) : ViewModel() {

    private val vmState = HomeVMState(
        stockPrices = listOf(
            Pair("THY", 3.2),
            Pair("FADE", -2.11),
            Pair("APPLE", 2.1),
            Pair("BSP", 1.4),
            Pair("META", -5.9),
        ),
        news = listOf("1", "2", "3", "4", "5", "6", "7", "8"," 9", "10")
    )
    private val _vmState = MutableStateFlow(vmState)
    val uiState: StateFlow<HomeVMState> = _vmState.asStateFlow()

    fun onNewsClicked(newsIndex: Int) {
        viewModelScope.launch {
            try {
                val a = investSmartService.getNews()
                Log.d("damla", a.toString())
                Log.d("damla", a.body().toString())
            } catch (e: Exception) {
                Log.d("damla", e.toString())
            }
        }

    /*
        val intentApp = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + newsIndex))
        val intentBrowser = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=dQw4w9WgXcQ"))

        try {
            context.startActivity(intentApp)
        } catch (ex: ActivityNotFoundException) {
            context.startActivity(intentBrowser)
        }*/
    }
}
