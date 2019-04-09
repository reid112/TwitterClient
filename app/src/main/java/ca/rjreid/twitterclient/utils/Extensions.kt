package ca.rjreid.twitterclient.utils

import android.content.ContextWrapper
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import ca.rjreid.twitterclient.GlideApp
import ca.rjreid.twitterclient.R

// Returns the parent activity of a given view
fun View.getParentActivity(): AppCompatActivity?{
    var context = this.context
    while (context is ContextWrapper) {
        if (context is AppCompatActivity) {
            return context
        }
        context = context.baseContext
    }
    return null
}

// Sets an image to an imageview
fun ImageView.image(url: String?, placeholder: Int = R.drawable.ic_placeholder, error: Int = R.drawable.ic_placeholder) {
    GlideApp
        .with(context.applicationContext)
        .load(url)
        .placeholder(placeholder)
        .error(error)
        .into(this)
}