package ca.rjreid.twitterclient.models

import java.util.*

data class Tweet(
    val userImageUrl: String,
    val username: String,
    val handle: String,
    val postDate: Date,
    val replyCount: Int,
    val retweetCount: Int,
    val likeCount: Int
)