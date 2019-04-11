package ca.rjreid.twitterclient.data.sharedprefs

import android.content.Context
import android.preference.PreferenceManager
import ca.rjreid.twitterclient.R
import ca.rjreid.twitterclient.models.TwitterUser


class DataManager(private val context: Context) : DataManagerDelegate {
    //region Variables
    private val prefs = PreferenceManager.getDefaultSharedPreferences(context)
    //endregion

    //region Implementations
    override fun login(user: TwitterUser) {
        prefs
            .edit()
            .putString(context.getString(R.string.pref_key_logged_in_username), user.username)
            .putString(context.getString(R.string.pref_key_logged_in_handle), user.handle)
            .putString(context.getString(R.string.pref_key_logged_in_image_url), user.userImageUrl)
            .apply()
    }

    override fun logout() {
        prefs
            .edit()
            .remove(context.getString(R.string.pref_key_logged_in_username))
            .remove(context.getString(R.string.pref_key_logged_in_handle))
            .remove(context.getString(R.string.pref_key_logged_in_image_url))
            .apply()
    }

    override fun isLoggedIn(): Boolean =
        !prefs.getString(context.getString(R.string.pref_key_logged_in_username), null).isNullOrBlank()

    override fun getLoggedInUser(): TwitterUser? {
        val username = prefs.getString(context.getString(R.string.pref_key_logged_in_username), null)
        val handle = prefs.getString(context.getString(R.string.pref_key_logged_in_handle), null)
        val imageUrl = prefs.getString(context.getString(R.string.pref_key_logged_in_image_url), null)

        if (username == null || handle == null || imageUrl == null) {
            return null
        }

        return TwitterUser(
            username = username,
            handle = handle,
            userImageUrl = imageUrl
        )
    }
    //endregion
}