package shvyn22.flexingfinalspace.util

import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import coil.request.ImageRequest
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import shvyn22.flexingfinalspace.R

fun ImageRequest.Builder.defaultRequests(): ImageRequest.Builder {
    return this
        .crossfade(true)
        .error(R.drawable.ic_error)
}

fun <T> Flow<T>.collectOnLifecycle(
    lifecycleOwner: LifecycleOwner,
    state: Lifecycle.State = Lifecycle.State.STARTED,
    block: suspend (value: T) -> Unit
) {
    lifecycleOwner.lifecycleScope.launch {
        lifecycleOwner.lifecycle.repeatOnLifecycle(state) {
            collect { block(it) }
        }
    }
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