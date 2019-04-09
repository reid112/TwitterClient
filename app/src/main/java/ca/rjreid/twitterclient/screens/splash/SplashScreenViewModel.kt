package ca.rjreid.twitterclient.screens.splash

import android.view.View
import androidx.lifecycle.MutableLiveData
import ca.rjreid.twitterclient.base.BaseViewModel
import ca.rjreid.twitterclient.data.DataManagerDelegate
import ca.rjreid.twitterclient.screens.list.ListActivity
import ca.rjreid.twitterclient.screens.login.LoginActivity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class SplashScreenViewModel(private val dataManagerDelegate: DataManagerDelegate) : BaseViewModel() {
    //region Variables
    private var loadingDisposable: Disposable? = null
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    //endregion

    //region Init
    init {
        load()
    }
    //endregion

    //region Helpers
    private fun load() {
        loadingDisposable?.dispose()

        Observable
            .just(true)
            .delay(2, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                loadingVisibility.value = View.VISIBLE
            }
            .doOnTerminate {
                loadingDisposable = null
                loadingVisibility.value = View.GONE
            }
            .subscribe(
                {
                    if (dataManagerDelegate.isLoggedIn()) {
                        startActivity(Pair(ListActivity::class, null))
                    } else {
                        startActivity(Pair(LoginActivity::class, null))
                    }
                },
                {

                }
            )
            .addTo(compositeDisposable)
    }
    //endregion
}