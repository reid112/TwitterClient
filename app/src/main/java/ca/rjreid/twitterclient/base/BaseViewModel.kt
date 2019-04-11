package ca.rjreid.twitterclient.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ca.rjreid.twitterclient.models.SingleUseEvent
import ca.rjreid.twitterclient.models.StartActivityInfo
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {
    //region Variables
    private val _activityToStart = MutableLiveData<SingleUseEvent<StartActivityInfo>>()
    val activityToStart: LiveData<SingleUseEvent<StartActivityInfo>>
        get() = _activityToStart

    private val _shouldFinish = MutableLiveData<Boolean>()
    val shouldFinish: LiveData<Boolean>
        get() = _shouldFinish

    val compositeDisposable = CompositeDisposable()
    //endregion

    //region Commands
    fun startActivity(startActivityInfo: StartActivityInfo) {
        _activityToStart.value = SingleUseEvent(startActivityInfo)
    }

    fun finish() {
        _shouldFinish.value = true
    }
    //endregion

    //region Overrides
    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
    //endregion
}