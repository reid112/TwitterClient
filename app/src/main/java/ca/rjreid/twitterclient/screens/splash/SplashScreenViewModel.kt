package ca.rjreid.twitterclient.screens.splash

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import ca.rjreid.twitterclient.base.BaseViewModel
import ca.rjreid.twitterclient.data.DataManagerDelegate
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class SplashScreenViewModel(
    private val dataManagerDelegate: DataManagerDelegate
) : BaseViewModel() {
    //region Variables
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    private lateinit var subscription: Disposable
    //endregion

    //region Init
    init {
        load()
    }
    //endregion

    //region Overrides
    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }
    //endregion

    //region Helpers
    private fun load() {
        subscription = Observable
            .just(true)
            .delay(2, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                loadingVisibility.value = View.VISIBLE
            }
            .doOnTerminate {
                loadingVisibility.value = View.GONE
            }
            .subscribe(
                {
                    if (dataManagerDelegate.isLoggedIn()) {
                        Log.d("REIDREIDREID", "Logged In")
                    } else {
                        Log.d("REIDREIDREID", "Logged Out")
                    }
                },
                {

                }
            )
    }
    //endregion
}