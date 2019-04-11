package ca.rjreid.twitterclient.screens.splash

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import ca.rjreid.twitterclient.R
import ca.rjreid.twitterclient.base.BaseActivity
import ca.rjreid.twitterclient.databinding.ActivitySplashScreenBinding
import javax.inject.Inject

class SplashScreenActivity : BaseActivity() {
    //region Variables
    @Inject lateinit var viewModelFactory: SplashScreenViewModelFactory
    private lateinit var binding: ActivitySplashScreenBinding

    override val viewModel: SplashScreenViewModel
        get() = ViewModelProviders.of(this, viewModelFactory).get(SplashScreenViewModel::class.java)

    override val layoutId: Int
        get() = R.layout.activity_splash_screen
    //endregion

    //region Lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, layoutId)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }
    //endregion
}