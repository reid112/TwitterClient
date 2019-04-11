package ca.rjreid.twitterclient.di.modules

import android.content.Context
import ca.rjreid.twitterclient.data.repository.Repository
import ca.rjreid.twitterclient.data.repository.RepositoryDelegate
import ca.rjreid.twitterclient.data.repository.database.TweetsDao
import ca.rjreid.twitterclient.data.repository.mockdata.MockDataSource
import ca.rjreid.twitterclient.data.repository.mockdata.TwitterDataSource
import ca.rjreid.twitterclient.data.sharedprefs.DataManager
import ca.rjreid.twitterclient.data.sharedprefs.DataManagerDelegate
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SingletonModule {
    @Provides
    @Singleton
    fun provideDataManagerDelegate(context: Context): DataManagerDelegate =
        DataManager(context)

    @Provides
    @Singleton
    fun provideMockDataSource(): TwitterDataSource =
        MockDataSource()

    @Provides
    @Singleton
    fun provideRepositoryDelegate(
        dataManagerDelegate: DataManagerDelegate,
        tweetsDao: TweetsDao,
        twitterDataSource: TwitterDataSource
    ): RepositoryDelegate =
        Repository(dataManagerDelegate, tweetsDao, twitterDataSource)
}