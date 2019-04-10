package ca.rjreid.twitterclient.di.modules

import android.content.Context
import ca.rjreid.twitterclient.data.DataManager
import ca.rjreid.twitterclient.data.DataManagerDelegate
import ca.rjreid.twitterclient.data.Repository
import ca.rjreid.twitterclient.data.RepositoryDelegate
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SingletonModule {
    @Provides
    @Singleton
    fun provideDataManagerDelegate(context: Context): DataManagerDelegate = DataManager(context)

    @Provides
    @Singleton
    fun provideRepositoryDelegate(dataManagerDelegate: DataManagerDelegate): RepositoryDelegate = Repository(dataManagerDelegate)
}