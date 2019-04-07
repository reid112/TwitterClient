package ca.rjreid.twitterclient.screens.splash

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import ca.rjreid.twitterclient.R
import ca.rjreid.twitterclient.base.BaseActivity
import ca.rjreid.twitterclient.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : BaseActivity() {
    //region Variables
    private lateinit var binding: ActivitySplashScreenBinding
    private lateinit var viewModel: SplashScreenViewModel
    //endregion

    //region Lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SplashScreenViewModel::class.java)

        binding = DataBindingUtil.setContentView(this, getLayoutId())
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }
    //endregion

    //region BaseActivity
    override fun getLayoutId(): Int = R.layout.activity_splash_screen
    //endregion
}