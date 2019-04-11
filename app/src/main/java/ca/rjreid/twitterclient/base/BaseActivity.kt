package ca.rjreid.twitterclient.base

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import ca.rjreid.twitterclient.R
import ca.rjreid.twitterclient.models.ActivityAnimation
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity : DaggerAppCompatActivity() {
    //region Abstract Properties
    abstract val viewModel: BaseViewModel
    @get:LayoutRes abstract val layoutId: Int
    //endregion

    //region Lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.activityToStart.observe(this, Observer {  singleUseEvent ->
            singleUseEvent.getContentIfNotHandled()?.let {
                val intent = Intent(this, it.activity.java)
                val bundle = it.bundle
                val enterAnim = it.enterAnimation
                val exitAnim = it.exitAnimation

                if (bundle != null) {
                    intent.putExtras(bundle)
                }

                startActivity(intent)

                if (enterAnim != null && exitAnim != null) {
                    overridePendingTransition(getAnimation(enterAnim), getAnimation(exitAnim))
                }
            }
        })

        viewModel.shouldFinish.observe(this, Observer { shouldFinish ->
            if (shouldFinish) {
                finish()
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

    //region Helpers
    fun initActionBar(toolbar: Toolbar, showTitle: Boolean, @DrawableRes homeAsUpIndicator: Int? = null) {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(showTitle)

        homeAsUpIndicator?.let {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setHomeAsUpIndicator(it)
        }
    }

    private fun getAnimation(anim: ActivityAnimation) =
            when (anim) {
                ActivityAnimation.STAY -> R.anim.stay
                ActivityAnimation.SLIDE_UP -> R.anim.slide_up
                ActivityAnimation.SLIDE_DOWN -> R.anim.slide_down
            }
    //endregion
}