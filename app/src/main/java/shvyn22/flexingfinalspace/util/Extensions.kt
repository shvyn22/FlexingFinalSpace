package shvyn22.flexingfinalspace.util

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.android.material.snackbar.Snackbar
import io.reactivex.rxjava3.core.Observable
import shvyn22.flexingfinalspace.FinalSpaceApp
import shvyn22.flexingfinalspace.R
import shvyn22.flexingfinalspace.di.component.SingletonComponent

fun RequestBuilder<Drawable>.defaultRequests(): RequestBuilder<Drawable> {
    return this
        .transition(DrawableTransitionOptions.withCrossFade())
        .error(R.drawable.ic_error)
}

fun <T> Observable<T>.toLiveData(): LiveData<T> =
    MutableLiveData<T>().apply {
        this@toLiveData.subscribe { postValue(it) }
    }

fun View.showError(e: Throwable) {
    Snackbar
        .make(
            this,
            context.getString(R.string.text_error, e.localizedMessage),
            Snackbar.LENGTH_LONG
        )
        .show()
}

val Context.singletonComponent: SingletonComponent
    get() = when (this) {
        is FinalSpaceApp -> singletonComponent
        else -> applicationContext.singletonComponent
    }
