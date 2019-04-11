package ca.rjreid.twitterclient.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tweets")
data class Tweet(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userImageUrl: String,
    val username: String,
    val handle: String,
    val content: String
)