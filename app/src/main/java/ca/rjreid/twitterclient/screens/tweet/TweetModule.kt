package ca.rjreid.twitterclient.screens.tweet

import ca.rjreid.twitterclient.data.repository.RepositoryDelegate
import dagger.Module
import dagger.Provides

@Module
class TweetModule {
    @Provides
    fun provideTweetViewModelFactory(repositoryDelegate: RepositoryDelegate): TweetViewModelFactory {
        return TweetViewModelFactory(repositoryDelegate)
    }
}