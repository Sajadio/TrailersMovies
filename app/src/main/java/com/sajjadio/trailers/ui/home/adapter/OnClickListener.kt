package com.sajjadio.trailers.ui.home.adapter

import com.sajjadio.trailers.utils.Destination

interface OnClickListener {
    fun onMoveToMovie(id: Int?)
    fun onSelectedDestination(destination: Destination)
}
