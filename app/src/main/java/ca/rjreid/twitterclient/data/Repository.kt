package ca.rjreid.twitterclient.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ca.rjreid.twitterclient.models.Tweet
import io.reactivex.Completable
import java.util.concurrent.TimeUnit

class Repository(
    private val dataManagerDelegate: DataManagerDelegate,
    private val dataSource: TwitterDataSource
) : RepositoryDelegate {
    //region Variables
    private val _currentTweets = MutableLiveData<List<Tweet>>()
    //endregion

    //region Init
    init {
        _currentTweets.value = dataSource.getInitialTweets()
    }
    //endregion

    //region Tweets
    override fun getTweets(): LiveData<List<Tweet>> = _currentTweets

    override fun addTweet(tweet: Tweet) {
        addTweetsToStartOfCurrentTweetsList(listOf(tweet))
    }

    override fun fetchNewTweets() {
        val currentTweets = _currentTweets.value
        val newTweets = dataSource.getNewTweets(currentTweets?.first()?.id ?: 0)

        addTweetsToStartOfCurrentTweetsList(newTweets)
    }

    override fun clearTweets() {
        _currentTweets.value = listOf()
    }
    //endregion

    // region Authentication
    override fun login(username: String, password: String) : Completable {
        return Completable
            .fromCallable {
                if (!dataSource.authenticate(username, password)) {
                    throw IllegalArgumentException("Login Failed")
                }

                dataManagerDelegate.login()
            }
            .delay(1, TimeUnit.SECONDS) // Add a delay to simulate a network request
    }

    override fun logout(): Completable =
            Completable.fromCallable { dataManagerDelegate.logout() }
    //endregion

    //region Helpers
    private fun addTweetsToStartOfCurrentTweetsList(tweetsToAdd: List<Tweet>) {
        val currentTweets = _currentTweets.value
        val newTweets = tweetsToAdd.toMutableList()

        currentTweets?.let {
            newTweets.addAll(it)
        }

        _currentTweets.value = newTweets
    }
    //endregion
}