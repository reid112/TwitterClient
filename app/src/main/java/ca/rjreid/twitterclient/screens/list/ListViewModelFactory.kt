package ca.rjreid.twitterclient.screens.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ca.rjreid.twitterclient.data.RepositoryDelegate

@Suppress("UNCHECKED_CAST")
class ListViewModelFactory(private val repositoryDelegate: RepositoryDelegate) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListViewModel::class.java)) {
            return ListViewModel(repositoryDelegate) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}