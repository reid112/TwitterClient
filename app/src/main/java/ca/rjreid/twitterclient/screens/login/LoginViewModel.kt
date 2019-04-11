package ca.rjreid.twitterclient.screens.login

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ca.rjreid.twitterclient.base.BaseViewModel
import ca.rjreid.twitterclient.data.repository.RepositoryDelegate
import ca.rjreid.twitterclient.models.SingleUseEvent
import ca.rjreid.twitterclient.models.StartActivityInfo
import ca.rjreid.twitterclient.screens.list.ListActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

class LoginViewModel(private val repositoryDelegate: RepositoryDelegate): BaseViewModel() {
    //region Variables
    private var loginDisposable: Disposable? = null

    private val _usernameBlankError = MutableLiveData<Boolean>()
    val usernameBlankError : LiveData<Boolean>
        get() = _usernameBlankError

    private val _passwordBlankError = MutableLiveData<Boolean>()
    val passwordBlankError : LiveData<Boolean>
        get() = _passwordBlankError

    private val _loginFailedError = MutableLiveData<SingleUseEvent<Boolean>>()
    val loginFailedError : LiveData<SingleUseEvent<Boolean>>
        get() = _loginFailedError

    val usernameEditTextValue = MutableLiveData<String>()
    val passwordEditTextValue = MutableLiveData<String>()

    val loginButtonVisibility = MutableLiveData<Int>()
    val loadingVisibility = MutableLiveData<Int>().apply { value = View.GONE }
    //endregion

    //region Click Handlers
    fun onLoginClick() {
        val username = usernameEditTextValue.value
        val password = passwordEditTextValue.value

        if (validateInputs(username, password)) {
            attemptLogin(username ?: "", password ?: "")
        }
    }
    //endregion

    //region Helpers
    private fun validateInputs(username: String?, password: String?): Boolean {
        var errors = false

        if (username.isNullOrBlank()) {
            errors = true
            _usernameBlankError.value = true
        } else {
            _usernameBlankError.value = false
        }

        if (password.isNullOrBlank()) {
            errors = true
            _passwordBlankError.value = true
        } else {
            _passwordBlankError.value = false
        }

        return !errors
    }

    private fun attemptLogin(username: String, password: String) {
        loginDisposable?.dispose()

        loginDisposable = repositoryDelegate
            .login(username, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                loadingVisibility.value = View.VISIBLE
                loginButtonVisibility.value = View.GONE
            }
            .doFinally {
                loginDisposable = null
                loadingVisibility.value = View.GONE
                loginButtonVisibility.value = View.VISIBLE
            }
            .subscribe(
                { loginSuccessful() },
                { loginFailed() }
            )
            .addTo(compositeDisposable)
    }

    private fun loginSuccessful() {
        _loginFailedError.value = SingleUseEvent(false)
        startActivity(StartActivityInfo(ListActivity::class))
        finish()
    }

    private fun loginFailed() {
        _loginFailedError.value = SingleUseEvent(true)
    }
    //endregion
}