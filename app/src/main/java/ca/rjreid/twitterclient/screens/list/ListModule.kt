package ca.rjreid.twitterclient.screens.list

import ca.rjreid.twitterclient.data.RepositoryDelegate
import dagger.Module
import dagger.Provides

@Module
class ListModule {
    @Provides
    fun provideListViewModelFactory(repositoryDelegate: RepositoryDelegate): ListViewModelFactory {
        return ListViewModelFactory(repositoryDelegate)
    }
}