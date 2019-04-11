package ca.rjreid.twitterclient.screens.splash

import ca.rjreid.twitterclient.data.sharedprefs.DataManagerDelegate
import dagger.Module
import dagger.Provides


@Module
class SplashScreenModule {
    @Provides
    fun provideSplashScreenViewModelFactory(
        dataManagerDelegate: DataManagerDelegate
    ): SplashScreenViewModelFactory {
        return SplashScreenViewModelFactory(dataManagerDelegate)
    }
}