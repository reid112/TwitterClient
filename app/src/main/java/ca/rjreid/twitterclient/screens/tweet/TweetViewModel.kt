package ca.rjreid.twitterclient.screens.tweet

import androidx.lifecycle.MutableLiveData
import ca.rjreid.twitterclient.base.BaseViewModel

class TweetViewModel() : BaseViewModel() {
    //region Variables
    val tweetEditTextValue = MutableLiveData<String>()
    //endregion

    //region Init
    init {

    }
    //endregion

    //region Commands
    fun tweet() {
        tweetEditTextValue.value?.let {
//            repositoryDelegate.addTweet(it)
        }
    }
    //endregion

    //region Helpers

    //endregion
}