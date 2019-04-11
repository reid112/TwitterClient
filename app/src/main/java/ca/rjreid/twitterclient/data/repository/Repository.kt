package ca.rjreid.twitterclient.data.repository

import androidx.lifecycle.LiveData
import ca.rjreid.twitterclient.data.repository.database.TweetsDao
import ca.rjreid.twitterclient.data.repository.mockdata.TwitterDataSource
import ca.rjreid.twitterclient.data.sharedprefs.DataManagerDelegate
import ca.rjreid.twitterclient.models.Tweet
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class Repository(
    private val dataManagerDelegate: DataManagerDelegate,
    private val tweetsDao: TweetsDao,
    private val dataSource: TwitterDataSource
) : RepositoryDelegate {
    //region Init
    init {
        // initialize the database with some tweets if it is empty
        val tweetCount =Single
            .fromCallable { tweetsDao.getTweetCount() }
            .subscribeOn(Schedulers.io())
            .blockingGet()

        if (tweetCount <= 0) {
            Completable
                .fromCallable {
                    tweetsDao.addTweets(dataSource.getInitialTweets())
                }
                .subscribeOn(Schedulers.io())
                .blockingAwait()
        }
    }
    //endregion

    //region Tweets
    override fun getTweets(): LiveData<List<Tweet>> = tweetsDao.getAllTweets()

    override fun addTweet(content: String) {
        Completable
            .fromCallable {
                val user = dataManagerDelegate.getLoggedInUser() ?: throw IllegalStateException("No logged in user!")
                val tweet = Tweet(
                    username = user.username,
                    handle = user.handle,
                    userImageUrl = user.userImageUrl,
                    content = content
                )
                tweetsDao.addTweet(tweet)
            }
            .subscribeOn(Schedulers.io())
            .blockingAwait()

    }

    override fun fetchNewTweets() {
        Completable
            .fromCallable {
                val newTweets = dataSource.getNewTweets() // Simulate pulling new tweets from an api
                tweetsDao.addTweets(newTweets)
            }
            .subscribeOn(Schedulers.io())
            .blockingAwait()

    }

    override fun clearTweets() {
        Completable
            .fromCallable {
                tweetsDao.clearTweets()
            }
            .subscribeOn(Schedulers.io())
            .blockingAwait()
    }
    //endregion

    // region Authentication
    override fun login(username: String, password: String) : Completable {
        return dataSource
            .authenticate(username, password)
            .flatMapCompletable { twitterUser ->
                dataManagerDelegate.login(twitterUser)
                Completable.complete()
            }
            .delay(1, TimeUnit.SECONDS) // Add a delay to simulate a network request
    }

    override fun logout(): Completable =
            Completable.fromCallable { dataManagerDelegate.logout() }
    //endregion
}