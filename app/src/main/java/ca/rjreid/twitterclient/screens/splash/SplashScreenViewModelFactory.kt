package ca.rjreid.twitterclient.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ca.rjreid.twitterclient.data.DataManagerDelegate
import ca.rjreid.twitterclient.rx.SchedulersFacade


@Suppress("UNCHECKED_CAST")
class SplashScreenViewModelFactory(
    private val dataManagerDelegate: DataManagerDelegate,
    private val schedulersFacade: SchedulersFacade
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SplashScreenViewModel::class.java)) {
            return SplashScreenViewModel(dataManagerDelegate, schedulersFacade) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}