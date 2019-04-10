package ca.rjreid.twitterclient.screens.list

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import ca.rjreid.twitterclient.R
import ca.rjreid.twitterclient.base.BaseActivity
import ca.rjreid.twitterclient.base.BaseViewModel
import ca.rjreid.twitterclient.databinding.ActivityListBinding
import kotlinx.android.synthetic.main.activity_list.*
import javax.inject.Inject


class ListActivity : BaseActivity() {
    //region Variables
    @Inject lateinit var viewModelFactory: ListViewModelFactory
    @Inject lateinit var listAdapter: ListAdapter
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

        initActionBar(toolbar, true)
        recyclerView.apply {
            adapter = listAdapter
            layoutManager = LinearLayoutManager(this@ListActivity)
            addItemDecoration(DividerItemDecoration(this@ListActivity, DividerItemDecoration.VERTICAL))
        }

        observeViewModel()
    }
    //endregion

    //region Options Menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_list, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menuLogout -> {
                viewModel.logout()
                true
            }
            else -> super.onOptionsItemSelected(item)
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
        viewModel.tweets.observe(this, Observer {
            listAdapter.submitList(it)
        })
    }
    //endregion
}