package ca.rjreid.twitterclient.data

interface DataManagerDelegate {
    fun login()
    fun logout()
    fun isLoggedIn(): Boolean
}