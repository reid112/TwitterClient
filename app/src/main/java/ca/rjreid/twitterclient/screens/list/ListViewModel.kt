package ca.rjreid.twitterclient.screens.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ca.rjreid.twitterclient.base.BaseViewModel
import ca.rjreid.twitterclient.data.repository.RepositoryDelegate
import ca.rjreid.twitterclient.models.ActivityAnimation
import ca.rjreid.twitterclient.models.StartActivityInfo
import ca.rjreid.twitterclient.models.Tweet
import ca.rjreid.twitterclient.screens.login.LoginActivity
import ca.rjreid.twitterclient.screens.tweet.TweetActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ListViewModel(private val repositoryDelegate: RepositoryDelegate) : BaseViewModel() {
    //region Variables
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private var disposable: Disposable? = null

    val tweets: LiveData<List<Tweet>>
        get() = repositoryDelegate.getTweets()
    //endregion

    //region Commands
    fun onAddClicked() {
        startActivity(
            StartActivityInfo(
                TweetActivity::class,
                null,
                ActivityAnimation.SLIDE_UP,
                ActivityAnimation.STAY
            )
        )
    }

    fun refresh() {
        repositoryDelegate.clearTweets()
        repositoryDelegate.fetchNewTweets()
        _isLoading.value = false
    }

    fun logout() {
        disposable?.dispose()

        disposable = repositoryDelegate
            .logout()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally { disposable?.dispose() }
            .subscribe {
                startActivity(StartActivityInfo(LoginActivity::class))
                finish()
            }
    }
    //endregion
}