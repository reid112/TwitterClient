package ca.rjreid.twitterclient.screens.tweet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class TweetViewModelFactory() : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TweetViewModel::class.java)) {
            return TweetViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}