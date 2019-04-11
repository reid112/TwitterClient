package ca.rjreid.twitterclient.data.repository.mockdata

import ca.rjreid.twitterclient.models.Tweet
import ca.rjreid.twitterclient.models.TwitterUser
import io.reactivex.Single

class MockDataSource : TwitterDataSource {
    //region Companion
    companion object {
        private const val USERNAME = "user123"
        private const val PASSWORD = "pass123"
        private const val userIcon = "https://www.shareicon.net/data/128x128/2015/09/24/106419_man_512x512.png"

        private val fakeUser1 = TwitterUser(
            username = "TwitterUser",
            handle = "@TwitterUser",
            userImageUrl = "http://invalid-url"
        )

        private val fakeUser2 = TwitterUser(
            username = "user123",
            handle = "@MrGreenShirt",
            userImageUrl = userIcon
        )

        private val users = listOf(
            fakeUser1,
            fakeUser2
        )

        // Used http://yes.thatcan.be/my/next/tweet/ to generate random tweet content
        private val tweetContent = listOf(
            "There's no public API for bug report, we're only support the app will fix. You can troubleshoot. trends.",
            "Please update to show a time. if you have a different network? It's likely something on for the App.",
            "Tap on the various sc… yes it can use the limits our list of diff icons/themes that from the app store.",
            "You an old version from Twitter. we're not sure your particular usage patterns. There's no we have to.",
            "Please log in the timeline, there's no public API for that from Twitter. double tapping will only display!",
            "It can take around the Gear button. once you try clearing your notifications are running version we can!"
        )
    }
    //endregion

    //region Overrides
    override fun authenticate(username: String, password: String): Single<TwitterUser> {
        return if (username == USERNAME && password == PASSWORD) {
            Single.just(users.find { it.username == username })
        } else {
            Single.error<TwitterUser>(Throwable("Invalid credentials"))
        }
    }

    override fun getInitialTweets(): List<Tweet> = generateTweets(1, 50)

    override fun getNewTweets(): List<Tweet> = generateTweets(1, 3)
    //endregion

    //region Helpers
    private fun generateTweets(startId: Int, endId: Int) : List<Tweet> {
        val tweets = mutableListOf<Tweet>()

        for (i in startId..endId) {
            tweets.add(
                Tweet(
                    userImageUrl = fakeUser1.userImageUrl,
                    username = fakeUser1.username,
                    handle = fakeUser1.handle,
                    content = tweetContent.random()
                )
            )
        }

        return tweets
    }
    //endregion
}