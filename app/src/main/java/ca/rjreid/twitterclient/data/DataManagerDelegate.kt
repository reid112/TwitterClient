package ca.rjreid.twitterclient.data

interface DataManagerDelegate {
    fun setLoggedIn(value: Boolean)
    fun isLoggedIn(): Boolean
}