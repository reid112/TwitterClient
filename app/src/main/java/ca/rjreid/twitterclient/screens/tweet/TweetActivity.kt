package ca.rjreid.twitterclient.screens.tweet

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import ca.rjreid.twitterclient.R
import ca.rjreid.twitterclient.base.BaseActivity
import ca.rjreid.twitterclient.base.BaseViewModel
import ca.rjreid.twitterclient.databinding.ActivityTweetBinding
import ca.rjreid.twitterclient.utils.hideKeyboard
import javax.inject.Inject

class TweetActivity : BaseActivity() {
    //region Variables
    @Inject lateinit var viewModelFactory: TweetViewModelFactory
    private lateinit var binding: ActivityTweetBinding
    private lateinit var viewModel: TweetViewModel
    //endregion

    //region Lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getViewModel() as TweetViewModel

        binding = DataBindingUtil.setContentView(this, getLayoutId())
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        initActionBar(binding.toolbar, false, R.drawable.ic_close)
    }

    override fun onResume() {
        super.onResume()
        binding.tweetEditText.requestFocus()
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.stay, R.anim.slide_down)
        binding.tweetEditText.hideKeyboard()
    }
    //endregion

    //region Options Menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_tweet, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menuTweet -> {
                viewModel.tweet()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    //endregion

    //region BaseActivity
    override fun getLayoutId(): Int = R.layout.activity_tweet

    override fun getViewModel(): BaseViewModel =
        ViewModelProviders.of(this, viewModelFactory).get(TweetViewModel::class.java)
    //endregion
}