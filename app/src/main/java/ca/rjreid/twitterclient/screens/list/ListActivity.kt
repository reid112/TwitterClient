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
import ca.rjreid.twitterclient.databinding.ActivityListBinding
import javax.inject.Inject


class ListActivity : BaseActivity() {
    //region Variables
    @Inject lateinit var viewModelFactory: ListViewModelFactory
    @Inject lateinit var listAdapter: ListAdapter
    private lateinit var binding: ActivityListBinding

    override val viewModel: ListViewModel
        get() = ViewModelProviders.of(this, viewModelFactory).get(ListViewModel::class.java)

    override val layoutId: Int
        get() = R.layout.activity_list
    //endregion

    //region Lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, layoutId)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        initActionBar(binding.toolbar, true)
        binding.recyclerView.apply {
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

    //region Helpers
    private fun observeViewModel() {
        viewModel.tweets.observe(this, Observer {
            listAdapter.submitList(it)
        })
    }
    //endregion
}