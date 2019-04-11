package ca.rjreid.twitterclient.data.repository.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ca.rjreid.twitterclient.models.Tweet

@Dao
interface TweetsDao {
    @Query("SELECT * FROM tweets ORDER BY id DESC")
    fun getAllTweets(): LiveData<List<Tweet>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addTweet(tweet: Tweet)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addTweets(tweets: List<Tweet>)

    @Query("SELECT COUNT(*) FROM tweets")
    fun getTweetCount(): Int

    @Query("DELETE FROM tweets")
    fun clearTweets()
}