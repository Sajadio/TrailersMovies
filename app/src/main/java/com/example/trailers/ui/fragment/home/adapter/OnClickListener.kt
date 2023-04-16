package com.example.trailers.ui.fragment.home.adapter

import com.example.trailers.utils.Destination

interface OnClickListener {
    fun onMoveToMovie(id: Int?)
    fun onSelectedDestination(destination: Destination)
}
