package com.example.movie.data.network

import com.example.movie.R
import com.example.movie.data.model.*
import com.example.movie.utils.ListAdapterItem
import com.example.movie.utils.ViewType

object ApiProvider {


    fun getAllItems(): MutableList<ListAdapterItem<Any>> {

        val list = mutableListOf<ListAdapterItem<Any>>()
        list.add(ListAdapterItem(getTrend(), ViewType.TREND))
        list.add(ListAdapterItem(getType(), ViewType.CHIPS_TYPE))
        list.add(ListAdapterItem(getCategory(), ViewType.CATEGORY))
        list.add(ListAdapterItem(R.string.popular, ViewType.TITLE))
        getPopular().map { list.add(ListAdapterItem(it, ViewType.POPULAR)) }
        return list
    }

    private fun getTrend(): MutableList<Trend> {
        val items = mutableListOf<Trend>()
        items.add(
            Trend(
                ids = 1,
                title = "SbiderMan",
                rate = 3.3f,
                type = getType(), posterId = R.drawable.ic_captain_marvel
            )
        )
        items.add(
            Trend(
                ids = 1,
                title = "SbiderMan",
                rate = 3.3f,
                type = getType(), posterId = R.drawable.ic_captain_marvel
            )
        )
        items.add(
            Trend(
                ids = 1,
                title = "SbiderMan",
                rate = 3.3f,
                type = getType(), posterId = R.drawable.ic_captain_marvel
            )
        )
        items.add(
            Trend(
                ids = 1,
                title = "SbiderMan",
                rate = 3.3f,
                type = getType(), posterId = R.drawable.ic_captain_marvel
            )
        )

        return items
    }

     fun getCategory(): MutableList<Category> {
        val items = mutableListOf<Category>()
        items.add(
            Category(
                ids = 1,
                title = "SbiderMan",
                rate = 3.3f,
                type = getType(), posterId = R.drawable.ic_captain_marvel,
                viewMore = true
            )
        )
        items.add(
            Category(
                ids = 1,
                title = "SbiderMan",
                rate = 3.3f,
                type = getType(), posterId = R.drawable.ic_captain_marvel,
                viewMore = true
            )
        )
        items.add(
            Category(
                ids = 1,
                title = "SbiderMan",
                rate = 3.3f,
                type = getType(), posterId = R.drawable.ic_captain_marvel,
                viewMore = true
            )
        )
        items.add(
            Category(
                ids = 1,
                title = "SbiderMan",
                rate = 3.3f,
                type = getType(), posterId = R.drawable.ic_captain_marvel,
                viewMore = true
            )
        )
        items.add(
            Category(
                ids = 1,
                title = "SbiderMan",
                rate = 3.3f,
                type = getType(), posterId = R.drawable.ic_captain_marvel,
                viewMore = true
            )
        )
        items.add(
            Category(
                ids = 1,
                title = "SbiderMan",
                rate = 3.3f,
                type = getType(), posterId = R.drawable.ic_captain_marvel,
                viewMore = true
            )
        )
        items.add(
            Category(
                ids = 1,
                title = "SbiderMan",
                rate = 3.3f,
                type = getType(), posterId = R.drawable.ic_captain_marvel,
                viewMore = true
            )
        )
        items.add(
            Category(
                ids = 1,
                title = "Steve",
                rate = 3.3f,
                type = getType(), posterId = R.drawable.img,
                viewMore = true
            )
        )
        return items
    }

    private fun getPopular(): MutableList<Popular> {
        val items = mutableListOf<Popular>()
        items.add(
            Popular(
                ids = 1,
                title = "SbiderMan",
                rate = 3.3f,
                type = getType(), posterId = R.drawable.ic_captain_marvel
            )
        )
        items.add(
            Popular(
                ids = 1,
                title = "SbiderMan",
                rate = 3.3f,
                type = getType(), posterId = R.drawable.ic_captain_marvel
            )
        )
        items.add(
            Popular(
                ids = 1,
                title = "SbiderMan",
                rate = 3.3f,
                type = getType(), posterId = R.drawable.ic_captain_marvel
            )
        )
        items.add(
            Popular(
                ids = 1,
                title = "SbiderMan",
                rate = 3.3f,
                type = getType(), posterId = R.drawable.ic_captain_marvel
            )
        )
        items.add(
            Popular(
                ids = 1,
                title = "SbiderMan",
                rate = 3.3f,
                type = getType(), posterId = R.drawable.ic_captain_marvel
            )
        )
        items.add(
            Popular(
                ids = 1,
                title = "SbiderMan",
                rate = 3.3f,
                type = getType(), posterId = R.drawable.ic_captain_marvel
            )
        )
        items.add(
            Popular(
                ids = 1,
                title = "SbiderMan",
                rate = 3.3f,
                type = getType(), posterId = R.drawable.ic_captain_marvel
            )
        )
        items.add(
            Popular(
                ids = 1,
                title = "SbiderMan",
                rate = 3.3f,
                type = getType(), posterId = R.drawable.ic_captain_marvel
            )
        )
        return items
    }

    private fun getType(): Type {

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
