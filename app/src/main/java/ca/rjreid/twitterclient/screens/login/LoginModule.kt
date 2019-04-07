package ca.rjreid.twitterclient.screens.login

import ca.rjreid.twitterclient.data.DataManagerDelegate
import dagger.Module
import dagger.Provides

@Module
class LoginModule {
    @Provides
    fun provideLoginViewModelFactory(
        dataManagerDelegate: DataManagerDelegate
    ): LoginViewModelFactory {
        return LoginViewModelFactory(dataManagerDelegate)
    }
}