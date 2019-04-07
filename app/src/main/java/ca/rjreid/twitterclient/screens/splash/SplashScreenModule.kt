package ca.rjreid.twitterclient.screens.splash

import ca.rjreid.twitterclient.SchedulersFacade
import ca.rjreid.twitterclient.data.Datamanager
import dagger.Module
import dagger.Provides


@Module
class SplashScreenModule {
    @Provides
    fun provideSplashScreenViewModelFactory(
        datamanager: Datamanager,
        schedulersFacade: SchedulersFacade
    ): SplashScreenViewModelFactory {
        return SplashScreenViewModelFactory(datamanager, schedulersFacade)
    }
}