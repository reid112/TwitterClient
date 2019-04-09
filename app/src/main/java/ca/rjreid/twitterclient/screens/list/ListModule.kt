package ca.rjreid.twitterclient.screens.list

import ca.rjreid.twitterclient.data.DataManagerDelegate
import dagger.Module
import dagger.Provides

@Module
class ListModule {
    @Provides
    fun provideListViewModelFactory(dataManagerDelegate: DataManagerDelegate): ListViewModelFactory {
        return ListViewModelFactory(dataManagerDelegate)
    }
}