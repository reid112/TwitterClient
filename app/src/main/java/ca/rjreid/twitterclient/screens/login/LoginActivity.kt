package ca.rjreid.twitterclient.screens.login

import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import ca.rjreid.twitterclient.R
import ca.rjreid.twitterclient.base.BaseActivity
import ca.rjreid.twitterclient.databinding.ActivityLoginBinding
import javax.inject.Inject

class LoginActivity : BaseActivity() {
    //region Variables
    @Inject lateinit var viewModelFactory: LoginViewModelFactory
    private lateinit var binding: ActivityLoginBinding

    override val viewModel: LoginViewModel
        get() = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel::class.java)

    override val layoutId: Int
        get() = R.layout.activity_login
    //endregion

    //region Lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, layoutId)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        observeViewModel()
    }
    //endregion

    //region Helpers
    private fun observeViewModel() {
        viewModel.usernameBlankError.observe(this, Observer {
            if (it) {
                binding.usernameInputLayout.error = getString(R.string.error_empty_username)
            } else {
                binding.usernameInputLayout.error = null
            }
        })

        viewModel.passwordBlankError.observe(this, Observer {
            if (it) {
                binding.passwordLayoutInput.error = getString(R.string.error_empty_password)
            } else {
                binding.passwordLayoutInput.error = null
            }
        })

        viewModel.loginFailedError.observe(this, Observer {
            it.getContentIfNotHandled()?.let { loginFailed ->
                if (loginFailed) {
                    Toast.makeText(this, getString(R.string.error_login_failed), Toast.LENGTH_LONG).show()
                }
            }
        })
    }
    //endregion
}