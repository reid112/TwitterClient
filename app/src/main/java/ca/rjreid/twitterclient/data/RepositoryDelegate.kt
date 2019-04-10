package ca.rjreid.twitterclient.data

import io.reactivex.Completable

interface RepositoryDelegate {
    fun login(username: String, password: String): Completable
    fun logout(): Completable
}