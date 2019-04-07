package ca.rjreid.twitterclient.base

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import kotlin.reflect.KClass

abstract class BaseViewModel : ViewModel() {
    val activityToStart = MutableLiveData<Pair<KClass<*>, Bundle?>>()
    val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}