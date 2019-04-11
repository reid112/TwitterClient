package ca.rjreid.twitterclient.screens.tweet

import dagger.Module
import dagger.Provides

@Module
class TweetModule {
    @Provides
    fun provideTweetViewModelFactory(): TweetViewModelFactory {
        return TweetViewModelFactory()
    }
}