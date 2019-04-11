package ca.rjreid.twitterclient.screens.tweet

import androidx.lifecycle.MutableLiveData
import ca.rjreid.twitterclient.base.BaseViewModel
import ca.rjreid.twitterclient.data.repository.RepositoryDelegate

class TweetViewModel(private val repositoryDelegate: RepositoryDelegate) : BaseViewModel() {
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
            repositoryDelegate.addTweet(it)
            finish()
        }
    }
    //endregion

    //region Helpers

    //endregion
}