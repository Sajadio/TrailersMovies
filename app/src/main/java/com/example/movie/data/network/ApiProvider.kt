package com.example.movie.data.network

import com.example.movie.R
import com.example.movie.data.m.Genres
import com.example.movie.data.m.Popular
import com.example.movie.data.m.Trend
import com.example.movie.data.m.Type
import com.example.movie.utils.ListHomeAdapterItem
import com.example.movie.utils.ViewTypeHome

object ApiProvider {

    fun getAllItems(): MutableList<ListHomeAdapterItem<Any>> {

        val list = mutableListOf<ListHomeAdapterItem<Any>>()
        list.add(ListHomeAdapterItem(getTrend(), ViewTypeHome.TREND))
        list.add(ListHomeAdapterItem(R.string.viewMore, ViewTypeHome.VIEW_MORE_CATEGORY))
        list.add(ListHomeAdapterItem(getCategory(), ViewTypeHome.GENERES))
        list.add(ListHomeAdapterItem(R.string.viewMore, ViewTypeHome.VIEW_MORE_POPULAR))
        getPopular().map { list.add(ListHomeAdapterItem(it, ViewTypeHome.POPULAR)) }

        return list
    }

    private fun getTrend(): MutableList<Trend> {
        val items = mutableListOf<Trend>()
        items.add(
            Trend(
                id = 1,
                title = "SbiderMan",
                rate = 3.3f,
                type = getType(), posterId = R.drawable.ic_captain_marvel
            )
        )
        items.add(
            Trend(
                id = 1,
                title = "SbiderMan",
                rate = 3.3f,
                type = getType(), posterId = R.drawable.ic_captain_marvel
            )
        )
        items.add(
            Trend(
                id = 1,
                title = "SbiderMan",
                rate = 3.3f,
                type = getType(), posterId = R.drawable.img
            )
        )
        items.add(
            Trend(
                id = 1,
                title = "SbiderMan",
                rate = 3.3f,
                type = getType(), posterId = R.drawable.ic_captain_marvel
            )
        )
        items.add(
            Trend(
                id = 1,
                title = "SbiderMan",
                rate = 3.3f,
                type = getType(), posterId = R.drawable.img
            )
        )
        items.add(
            Trend(
                id = 1,
                title = "SbiderMan",
                rate = 3.3f,
                type = getType(), posterId = R.drawable.ic_captain_marvel
            )
        )
        items.add(
            Trend(
                id = 1,
                title = "SbiderMan",
                rate = 3.3f,
                type = getType(), posterId = R.drawable.img
            )
        )

        return items
    }

    fun getCategory(): MutableList<Genres> {
        val items = mutableListOf<Genres>()
        items.add(
            Genres(
                id = 1,
                title = "SbiderMan",
                rate = 3.3f,
                type = getType(), posterId = R.drawable.ic_captain_marvel,
            )
        )
        items.add(
            Genres(
                id = 1,
                title = "SbiderMan",
                rate = 3.3f,
                type = getType(), posterId = R.drawable.ic_captain_marvel,

                )
        )
        items.add(
            Genres(
                id = 1,
                title = "SbiderMan",
                rate = 3.3f,
                type = getType(), posterId = R.drawable.ic_captain_marvel,

                )
        )
        items.add(
            Genres(
                id = 1,
                title = "SbiderMan",
                rate = 3.3f,
                type = getType(), posterId = R.drawable.ic_captain_marvel,

                )
        )
        items.add(
            Genres(
                id = 1,
                title = "SbiderMan",
                rate = 3.3f,
                type = getType(), posterId = R.drawable.ic_captain_marvel,

                )
        )
        items.add(
            Genres(
                id = 1,
                title = "SbiderMan",
                rate = 3.3f,
                type = getType(), posterId = R.drawable.ic_captain_marvel,

                )
        )
        items.add(
            Genres(
                id = 1,
                title = "SbiderMan",
                rate = 3.3f,
                type = getType(), posterId = R.drawable.ic_captain_marvel,

                )
        )
        items.add(
            Genres(
                id = 1,
                title = "Steve",
                rate = 3.3f,
                type = getType(), posterId = R.drawable.img,

                )
        )
        items.add(
            Genres(
                id = 1,
                title = "SbiderMan",
                rate = 3.3f,
                type = getType(), posterId = R.drawable.ic_captain_marvel,

                )
        )
        return items
    }

    private fun getPopular(): MutableList<Popular> {
        val items = mutableListOf<Popular>()
        items.add(
            Popular(
                id = 1,
                title = "SbiderMan",
                rate = 3.3f,
                type = getType(), posterId = R.drawable.ic_captain_marvel
            )
        )
        items.add(
            Popular(
                id = 1,
                title = "SbiderMan",
                rate = 3.3f,
                type = getType(), posterId = R.drawable.ic_captain_marvel
            )
        )
        items.add(
            Popular(
                id = 1,
                title = "SbiderMan",
                rate = 3.3f,
                type = getType(), posterId = R.drawable.ic_captain_marvel
            )
        )
        items.add(
            Popular(
                id = 1,
                title = "SbiderMan",
                rate = 3.3f,
                type = getType(), posterId = R.drawable.ic_captain_marvel
            )
        )
        items.add(
            Popular(
                id = 1,
                title = "SbiderMan",
                rate = 3.3f,
                type = getType(), posterId = R.drawable.ic_captain_marvel
            )
        )
        items.add(
            Popular(
                id = 1,
                title = "SbiderMan",
                rate = 3.3f,
                type = getType(), posterId = R.drawable.ic_captain_marvel
            )
        )
        items.add(
            Popular(
                id = 1,
                title = "SbiderMan",
                rate = 3.3f,
                type = getType(), posterId = R.drawable.ic_captain_marvel
            )
        )
        items.add(
            Popular(
                id = 1,
                title = "SbiderMan",
                rate = 3.3f,
                type = getType(), posterId = R.drawable.ic_captain_marvel
            )
        )
        items.add(
            Popular(
                id = 1,
                title = "SbiderMan",
                rate = 3.3f,
                type = getType(), posterId = R.drawable.img
            )
        )
        return items
    }

    fun getType(): Type {

        val listType = mutableListOf<String>()
        listType.add("Action")
        listType.add("Comide")
        listType.add("Comide")
        listType.add("Comide")
        listType.add("Drama")
        listType.add("Action")
        listType.add("Drama")

        return Type(
            ids = 1,
            type = listType
        )

    }


}
