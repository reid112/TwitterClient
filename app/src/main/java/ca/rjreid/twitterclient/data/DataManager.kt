package ca.rjreid.twitterclient.data

import android.content.Context
import android.preference.PreferenceManager
import ca.rjreid.twitterclient.R


class DataManager(private val context: Context) : DataManagerDelegate {
    //region Variables
    private val prefs = PreferenceManager.getDefaultSharedPreferences(context)
    //endregion

    //region Implementations
    override fun setLoggedIn(value: Boolean) {
        prefs.edit().putBoolean(context.getString(R.string.pref_key_is_logged_in), value).apply()
    }

    override fun isLoggedIn(): Boolean = prefs.getBoolean(context.getString(R.string.pref_key_is_logged_in), false)
    //endregion
}