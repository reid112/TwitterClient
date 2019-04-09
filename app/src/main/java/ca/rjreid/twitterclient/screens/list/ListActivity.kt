package ca.rjreid.twitterclient.screens.list

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import ca.rjreid.twitterclient.R
import ca.rjreid.twitterclient.base.BaseActivity
import ca.rjreid.twitterclient.base.BaseViewModel
import ca.rjreid.twitterclient.databinding.ActivityListBinding
import kotlinx.android.synthetic.main.activity_list.*
import javax.inject.Inject


class ListActivity : BaseActivity() {
    //region Variables
    @Inject lateinit var viewModelFactory: ListViewModelFactory
    private lateinit var binding: ActivityListBinding
    private lateinit var viewModel: ListViewModel
    //endregion

    //region Lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getViewModel() as ListViewModel

        binding = DataBindingUtil.setContentView(this, getLayoutId())
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        initActionBar(toolbar)
        observeViewModel()
    }
    //endregion

    //region Options Menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_list, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuLogout -> {
                viewModel.logout()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    //endregion

    //region BaseActivity
    override fun getLayoutId(): Int = R.layout.activity_list

    override fun getViewModel(): BaseViewModel =
        ViewModelProviders.of(this, viewModelFactory).get(ListViewModel::class.java)
    //endregion

    //region Helpers
    private fun observeViewModel() {

    }
    //endregion
}