package ca.rjreid.twitterclient.screens.splash

import ca.rjreid.twitterclient.data.DataManagerDelegate
import ca.rjreid.twitterclient.rx.SchedulersFacade
import dagger.Module
import dagger.Provides


@Module
class SplashScreenModule {
    @Provides
    fun provideSplashScreenViewModelFactory(
        dataManagerDelegate: DataManagerDelegate,
        schedulersFacade: SchedulersFacade
    ): SplashScreenViewModelFactory {
        return SplashScreenViewModelFactory(dataManagerDelegate, schedulersFacade)
    }
}