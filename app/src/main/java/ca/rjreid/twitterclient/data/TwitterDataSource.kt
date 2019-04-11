package ca.rjreid.twitterclient.data

import ca.rjreid.twitterclient.models.Tweet

interface TwitterDataSource {
    fun authenticate(username: String, password: String): Boolean
    fun getInitialTweets(): List<Tweet>
    fun getNewTweets(lastTweetId: Int): List<Tweet>
}