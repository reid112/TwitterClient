package ca.rjreid.twitterclient.data.sharedprefs

import ca.rjreid.twitterclient.models.TwitterUser

interface DataManagerDelegate {
    fun login(user: TwitterUser)
    fun logout()
    fun isLoggedIn(): Boolean
    fun getLoggedInUser(): TwitterUser?
}