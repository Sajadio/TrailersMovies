package com.sajjadio.trailers.utils

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

open class Event<out T>(private val content: T) {
    private var hasBeenHandled = false

    fun getContentIfHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    fun peekContent(): T = content
}

class EventObserver<T>(private val onEventUnhandledContent: (T) -> Unit) : Observer<Event<T>> {
    override fun onChanged(event: Event<T>) {
        event.getContentIfHandled()?.let { onEventUnhandledContent(it) }
    }
}

fun <T> LiveData<Event<T>>.observeEvent(owner: LifecycleOwner, function: (T) -> Unit) {
    this.observe(owner, EventObserver { function(it) })
}