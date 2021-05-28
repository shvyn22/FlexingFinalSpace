package shvyn22.myapplication.util

import android.graphics.drawable.Drawable
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import shvyn22.myapplication.R

fun RequestBuilder<Drawable>.defaultRequests(): RequestBuilder<Drawable> {
    return this
        .transition(DrawableTransitionOptions.withCrossFade())
        .error(R.drawable.ic_error)
}