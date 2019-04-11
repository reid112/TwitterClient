package ca.rjreid.twitterclient.di.modules

import android.content.Context
import ca.rjreid.twitterclient.data.*
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
    fun provideMockDataSource(): TwitterDataSource = MockDataSource()

    @Provides
    @Singleton
    fun provideRepositoryDelegate(dataManagerDelegate: DataManagerDelegate, twitterDataSource: TwitterDataSource): RepositoryDelegate =
        Repository(dataManagerDelegate, twitterDataSource)
}