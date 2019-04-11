package ca.rjreid.twitterclient.data.repository.mockdata

import ca.rjreid.twitterclient.models.Tweet
import ca.rjreid.twitterclient.models.TwitterUser
import io.reactivex.Single

interface TwitterDataSource {
    fun authenticate(username: String, password: String): Single<TwitterUser>
    fun getInitialTweets(): List<Tweet>
    fun getNewTweets(): List<Tweet>
}