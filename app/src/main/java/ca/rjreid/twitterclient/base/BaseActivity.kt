package ca.rjreid.twitterclient.base

import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.Toolbar
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity : DaggerAppCompatActivity() {
    //region Lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
    }
    //endregion

    //region Menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }

        return super.onOptionsItemSelected(item)
    }
    //endregion

    //region Abstract Methods
    abstract fun getLayoutId(): Int
    //endregion


    //region Helpers
    fun initActionBar(toolbar: Toolbar, @DrawableRes homeAsUpIndicator: Int) {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(homeAsUpIndicator)
    }
    //endregion
}