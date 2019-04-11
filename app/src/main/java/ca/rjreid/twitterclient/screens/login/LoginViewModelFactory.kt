package ca.rjreid.twitterclient.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ca.rjreid.twitterclient.data.repository.RepositoryDelegate

@Suppress("UNCHECKED_CAST")
class LoginViewModelFactory(private val repositoryDelegate: RepositoryDelegate) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(repositoryDelegate) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}