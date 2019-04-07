package ca.rjreid.twitterclient.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ca.rjreid.twitterclient.data.DataManagerDelegate


@Suppress("UNCHECKED_CAST")
class SplashScreenViewModelFactory(
    private val dataManagerDelegate: DataManagerDelegate
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SplashScreenViewModel::class.java)) {
            return SplashScreenViewModel(dataManagerDelegate) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}