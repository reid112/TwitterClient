package ca.rjreid.twitterclient.screens.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ca.rjreid.twitterclient.base.BaseViewModel
import ca.rjreid.twitterclient.data.DataManagerDelegate
import ca.rjreid.twitterclient.models.Tweet
import ca.rjreid.twitterclient.screens.login.LoginActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

class ListViewModel(private val dataManagerDelegate: DataManagerDelegate) : BaseViewModel() {
    //region Variables
    private val _tweets = MutableLiveData<List<Tweet>>()
    val tweets: LiveData<List<Tweet>>
        get() = _tweets
    //endregion

    //region Init
    init {
        _tweets.value = listOf(
            getTweet(1),
            getTweet(2),
            getTweet(3),
            getTweet(4),
            getTweet(5),
            getTweet(6),
            getTweet(7),
            getTweet(8),
            getTweet(9),
            getTweet(10),
            getTweet(11),
            getTweet(12),
            getTweet(13),
            getTweet(14),
            getTweet(15),
            getTweet(16),
            getTweet(17),
            getTweet(18),
            getTweet(19),
            getTweet(20)
        )

        AndroidSchedulers.mainThread().scheduleDirect({
            val tweets = _tweets.value
            val newTweets = mutableListOf(
                getTweet(21),
                getTweet(22),
                getTweet(23),
                getTweet(24),
                getTweet(25),
                getTweet(26),
                getTweet(27),
                getTweet(28),
                getTweet(29),
                getTweet(30)
            )

            tweets?.let {
                newTweets.addAll(it)
            }

            _tweets.value = newTweets

        }, 20, TimeUnit.SECONDS)
    }
    //endregion

    //region Commands
    fun onAddClicked() {
        Log.d("REIDREIDREID", "Add Clicked")
    }

    fun logout() {
        dataManagerDelegate.logout()
        startActivity(Pair(LoginActivity::class, null))
    }
    //endregion

    //region Helpers
    private fun getTweet(id: Int) : Tweet {
        return Tweet(id, "", "name-$id", "handle-$id", "$id : this is the content of the tweet")
    }
    //endregion
}