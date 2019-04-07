package ca.rjreid.twitterclient.screens.login

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import ca.rjreid.twitterclient.R
import ca.rjreid.twitterclient.base.BaseActivity
import ca.rjreid.twitterclient.base.BaseViewModel
import ca.rjreid.twitterclient.databinding.ActivityLoginBinding
import javax.inject.Inject

class LoginActivity : BaseActivity() {
    //region Variables
    @Inject lateinit var viewModelFactory: LoginViewModelFactory
    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel
    //endregion

    //region Lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getViewModel() as LoginViewModel

        binding = DataBindingUtil.setContentView(this, getLayoutId())
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }
    //endregion

    //region BaseActivity
    override fun getLayoutId(): Int = R.layout.activity_login

    override fun getViewModel(): BaseViewModel =
        ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel::class.java)
    //endregion
}