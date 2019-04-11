package ca.rjreid.twitterclient.screens.login

import ca.rjreid.twitterclient.data.repository.RepositoryDelegate
import dagger.Module
import dagger.Provides

@Module
class LoginModule {
    @Provides
    fun provideLoginViewModelFactory(
        repositoryDelegate: RepositoryDelegate
    ): LoginViewModelFactory {
        return LoginViewModelFactory(repositoryDelegate)
    }
}