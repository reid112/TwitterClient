package ca.rjreid.twitterclient.base

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity : DaggerAppCompatActivity() {
    //region Lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())

        getViewModel().activityToStart.observe(this, Observer { value ->
            value.getContentIfNotHandled()?.let {
                val intent = Intent(this, it.first.java)
                val bundle = it.second

                if (bundle != null) {
                    intent.putExtras(bundle)
                }

                startActivity(intent)
            }
        })
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

    abstract fun getViewModel(): BaseViewModel
    //endregion


    //region Helpers
    fun initActionBar(toolbar: Toolbar, @DrawableRes homeAsUpIndicator: Int) {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(homeAsUpIndicator)
    }
    //endregion
}