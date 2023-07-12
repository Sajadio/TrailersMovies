package com.sajjadio.trailers.ui.persons

import com.sajjadio.trailers.R
import com.sajjadio.trailers.domain.model.Cast
import com.sajjadio.trailers.ui.base.BaseAdapter
import com.sajjadio.trailers.ui.base.BaseInteractListener

class PersonsAdapter(
    items: List<Cast>,
    listener: PersonInteractListener
) : BaseAdapter<Cast>(items, listener) {
    override var layoutId = R.layout.layout_item_person_card
}
interface PersonInteractListener : BaseInteractListener