package ca.rjreid.twitterclient.activities

import android.os.Bundle
import ca.rjreid.twitterclient.R
import ca.rjreid.twitterclient.base.BaseActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

class SplashScreenActivity : BaseActivity() {

    //region Lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSchedulers.mainThread().scheduleDirect({
            finish()
        }, 3, TimeUnit.SECONDS) // Add a delay here that simulates a web call to initialize the app
    }
    //endregion

    //region BaseActivity
    override fun getLayoutId(): Int = R.layout.activity_splash_screen
    //endregion
}