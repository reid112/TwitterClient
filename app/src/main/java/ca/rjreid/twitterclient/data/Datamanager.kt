package ca.rjreid.twitterclient.data

import android.content.Context
import android.preference.PreferenceManager
import ca.rjreid.twitterclient.R
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Datamanager @Inject constructor(private val context: Context) {
    //region Variables
    private val prefs = PreferenceManager.getDefaultSharedPreferences(context)
    //endregion

    fun setLoggedIn(value: Boolean) {
        prefs.edit().putBoolean(context.getString(R.string.pref_key_is_logged_in), value).apply()
    }

    fun isLoggedIn(): Boolean = prefs.getBoolean(context.getString(R.string.pref_key_is_logged_in), false)
}