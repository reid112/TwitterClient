package ca.rjreid.twitterclient.models

data class Tweet(
    val id: Int,
    val userImageUrl: String,
    val username: String,
    val handle: String,
    val content: String
)