package ca.rjreid.twitterclient.screens.tweet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ca.rjreid.twitterclient.data.repository.RepositoryDelegate

@Suppress("UNCHECKED_CAST")
class TweetViewModelFactory(private val repositoryDelegate: RepositoryDelegate) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TweetViewModel::class.java)) {
            return TweetViewModel(repositoryDelegate) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}