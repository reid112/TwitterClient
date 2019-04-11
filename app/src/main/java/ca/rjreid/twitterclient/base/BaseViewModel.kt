package ca.rjreid.twitterclient.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ca.rjreid.twitterclient.data.SingleUseEvent
import ca.rjreid.twitterclient.data.StartActivityInfo
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {
    //region Variables
    private val _activityToStart = MutableLiveData<SingleUseEvent<StartActivityInfo>>()
    val activityToStart: LiveData<SingleUseEvent<StartActivityInfo>>
        get() = _activityToStart

    val compositeDisposable = CompositeDisposable()
    //endregion

    //region Commands
    fun startActivity(startActivityInfo: StartActivityInfo) {
        _activityToStart.value = SingleUseEvent(startActivityInfo)
    }
    //endregion

    //region Overrides
    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
    //endregion
}