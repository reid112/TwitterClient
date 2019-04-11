package ca.rjreid.twitterclient.data.repository.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ca.rjreid.twitterclient.models.Tweet

@Database(entities = [Tweet::class], version = 1)
abstract class TwitterDatabase : RoomDatabase() {
    abstract fun tweetsDao(): TweetsDao
}