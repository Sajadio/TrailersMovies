package com.sajjadio.trailers.ui.actors

import com.sajjadio.trailers.ui.base.BaseInteractListener

interface ActorInteractListener : BaseInteractListener {
    fun onActorItemClick(id: Int)
}