package ca.rjreid.twitterclient.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ca.rjreid.twitterclient.SchedulersFacade
import ca.rjreid.twitterclient.data.Datamanager


@Suppress("UNCHECKED_CAST")
class SplashScreenViewModelFactory(
    private val datamanager: Datamanager,
    private val schedulersFacade: SchedulersFacade
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SplashScreenViewModel::class.java)) {
            return SplashScreenViewModel(datamanager, schedulersFacade) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}