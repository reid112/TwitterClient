package ca.rjreid.twitterclient.screens.list

import ca.rjreid.twitterclient.BaseTest
import ca.rjreid.twitterclient.screens.login.LoginActivity
import ca.rjreid.twitterclient.screens.tweet.TweetActivity
import com.jraska.livedata.test
import com.nhaarman.mockito_kotlin.inOrder
import com.nhaarman.mockito_kotlin.then
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import org.junit.Before
import org.junit.Test

class ListViewModelTest : BaseTest() {
    //region Variables
    private lateinit var viewModel: ListViewModel
    //endregion

    //region Setup
    @Before
    fun setup() {
        viewModel = ListViewModel(repositoryDelegate)

        whenever(repositoryDelegate.logout()).thenReturn(Completable.complete())
    }
    //endregion

    //region Tests
    @Test
    fun `GIVEN fetch new tweets not yet called WHEN fetch new tweets THEN fetch new tweets`() {
        viewModel.fetchNewTweets()

        then(repositoryDelegate).should().fetchNewTweets()
    }

    @Test
    fun `GIVEN fetch new tweets called multiple times WHEN fetch new tweets THEN fetch new tweets once`() {
        viewModel.fetchNewTweets()
        viewModel.fetchNewTweets()
        viewModel.fetchNewTweets()

        then(repositoryDelegate).should(times(1)).fetchNewTweets()
    }

    @Test
    fun `WHEN add clicked THEN show tweet activity`() {
        viewModel.onAddClicked()

        viewModel
            .activityToStart
            .test()
            .assertValue {
                it.peekValue().activity == TweetActivity::class
            }
    }

    @Test
    fun `WHEN refresh THEN refresh tweets`() {
        viewModel.refresh()

        inOrder(repositoryDelegate) {
            verify(repositoryDelegate).clearTweets()
            verify(repositoryDelegate).fetchNewTweets()
        }

        viewModel
            .isLoading
            .test()
            .assertValue(false)
    }

    @Test
    fun `GIVEN logout successful WHEN logout THEN start login activity`() {
        viewModel.logout()

        viewModel
            .activityToStart
            .test()
            .assertValue {
                it.peekValue().activity == LoginActivity::class
            }
    }
    //endregion
}