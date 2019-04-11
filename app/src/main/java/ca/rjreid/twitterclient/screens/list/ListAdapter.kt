package ca.rjreid.twitterclient.screens.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ca.rjreid.twitterclient.R
import ca.rjreid.twitterclient.models.Tweet
import ca.rjreid.twitterclient.screens.list.ListAdapter.TweetViewHolder
import ca.rjreid.twitterclient.utils.image
import kotlinx.android.synthetic.main.list_item_tweet.view.*
import javax.inject.Inject

class ListAdapter @Inject constructor() : ListAdapter<Tweet, TweetViewHolder>(DiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TweetViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return TweetViewHolder(inflater.inflate(R.layout.list_item_tweet, parent, false))
    }

    override fun onBindViewHolder(holder: TweetViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    class DiffCallback : DiffUtil.ItemCallback<Tweet>() {
        override fun areItemsTheSame(oldItem: Tweet, newItem: Tweet): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Tweet, newItem: Tweet): Boolean {
            return oldItem == newItem
        }
    }

    class TweetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(tweet: Tweet) {
            itemView.apply {
                imageView.image(tweet.userImageUrl)
                username.text = tweet.username
                handle.text = tweet.handle
                content.text = tweet.content
            }
        }
    }
}