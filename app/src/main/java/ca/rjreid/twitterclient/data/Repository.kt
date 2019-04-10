package ca.rjreid.twitterclient.data

import io.reactivex.Completable
import java.util.concurrent.TimeUnit

class Repository(val dataManagerDelegate: DataManagerDelegate) : RepositoryDelegate {
    companion object {
        private const val USERNAME = "user123"
        private const val PASSWORD = "pass123"
    }

    override fun login(username: String, password: String) : Completable {
        return Completable
            .fromCallable {
                if (username != USERNAME || password != PASSWORD) {
                    throw IllegalArgumentException("Login Failed")
                }

                dataManagerDelegate.login()
            }
            .delay(1, TimeUnit.SECONDS) // Add a delay to simulate a network request
    }

    override fun logout(): Completable =
            Completable.fromCallable { dataManagerDelegate.logout() }

}