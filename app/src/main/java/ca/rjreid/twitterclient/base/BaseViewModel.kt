package ca.rjreid.twitterclient.base

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ca.rjreid.twitterclient.data.SingleUseEvent
import io.reactivex.disposables.CompositeDisposable
import kotlin.reflect.KClass

abstract class BaseViewModel : ViewModel() {
    //region Variables
    private val _activityToStart = MutableLiveData<SingleUseEvent<Pair<KClass<*>, Bundle?>>>()
    val activityToStart: LiveData<SingleUseEvent<Pair<KClass<*>, Bundle?>>>
        get() = _activityToStart

    val compositeDisposable = CompositeDisposable()
    //endregion

    //region Commands
    fun startActivity(classBundlePair: Pair<KClass<*>, Bundle?>) {
        _activityToStart.value = SingleUseEvent(classBundlePair)
    }
    //endregion

    //region Overrides
    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
    //endregion
}