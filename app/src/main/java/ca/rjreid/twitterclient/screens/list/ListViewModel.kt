package ca.rjreid.twitterclient.screens.list

import ca.rjreid.twitterclient.base.BaseViewModel
import ca.rjreid.twitterclient.data.DataManagerDelegate
import ca.rjreid.twitterclient.screens.login.LoginActivity

class ListViewModel(private val dataManagerDelegate: DataManagerDelegate) : BaseViewModel() {
    //region Variables

    //endregion

    //region Init
    init {

    }
    //endregion

    //region Commands
    fun logout() {
        dataManagerDelegate.logout()
        startActivity(Pair(LoginActivity::class, null))
    }
    //endregion

    //region Helpers

    //endregion
}