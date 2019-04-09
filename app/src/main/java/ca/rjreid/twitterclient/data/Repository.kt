package ca.rjreid.twitterclient.data

import io.reactivex.Single
import java.util.concurrent.TimeUnit

object Repository {
    private const val USERNAME = "user123"
    private const val PASSWORD = "pass123"

    fun login(username: String, password: String) =
        Single
            .fromCallable { (username == USERNAME && password == PASSWORD) }
            .delay(2, TimeUnit.SECONDS)

}