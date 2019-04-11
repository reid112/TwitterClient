package ca.rjreid.twitterclient.data

import androidx.lifecycle.LiveData
import ca.rjreid.twitterclient.models.Tweet
import io.reactivex.Completable

interface RepositoryDelegate {
    fun getTweets(): LiveData<List<Tweet>>
    fun addTweet(tweet: Tweet)
    fun fetchNewTweets()
    fun clearTweets()
    fun login(username: String, password: String): Completable
    fun logout(): Completable
}