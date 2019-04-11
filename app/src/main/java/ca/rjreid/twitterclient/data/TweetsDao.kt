package ca.rjreid.twitterclient.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ca.rjreid.twitterclient.models.Tweet

@Dao
interface TweetsDao {
    @Query("SELECT * FROM tweets")
    fun getAllTweets(): List<Tweet>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addTweet(tweet: Tweet)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addTweets(tweets: List<Tweet>)
}