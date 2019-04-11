package ca.rjreid.twitterclient.di.modules

import android.app.Application
import androidx.room.Room
import ca.rjreid.twitterclient.data.TweetsDao
import ca.rjreid.twitterclient.data.TwitterDatabase
import dagger.Module
import dagger.Provides

@Module
class RoomModule {
    companion object {
        private val DATABASE_NAME = "twitter_database"
    }

    @Provides
    internal fun providesTwitterDatabase(application: Application): TwitterDatabase {
        return Room
            .databaseBuilder(application, TwitterDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    internal fun providesTweetsDao(database: TwitterDatabase): TweetsDao {
        return database.tweetsDao()
    }
}