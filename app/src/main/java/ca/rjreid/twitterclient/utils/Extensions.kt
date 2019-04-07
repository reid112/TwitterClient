package ca.rjreid.twitterclient.utils

import android.content.ContextWrapper
import android.view.View
import androidx.appcompat.app.AppCompatActivity

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