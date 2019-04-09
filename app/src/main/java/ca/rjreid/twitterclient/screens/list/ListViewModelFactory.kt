package ca.rjreid.twitterclient.screens.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ca.rjreid.twitterclient.data.DataManagerDelegate

@Suppress("UNCHECKED_CAST")
class ListViewModelFactory(private val dataManagerDelegate: DataManagerDelegate) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListViewModel::class.java)) {
            return ListViewModel(dataManagerDelegate) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}