package ca.rjreid.twitterclient.screens.splash

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import ca.rjreid.twitterclient.SchedulersFacade
import ca.rjreid.twitterclient.base.BaseViewModel
import ca.rjreid.twitterclient.data.Datamanager
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit

class SplashScreenViewModel(
    private val datamanager: Datamanager,
    private val schedulersFacade: SchedulersFacade
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
            .delay(5, TimeUnit.SECONDS)
            .subscribeOn(schedulersFacade.io())
            .observeOn(schedulersFacade.ui())
            .doOnSubscribe {
                loadingVisibility.value = View.VISIBLE
            }
            .doOnTerminate {
                loadingVisibility.value = View.GONE
            }
            .subscribe(
                {
                    if (datamanager.isLoggedIn()) {
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