package com.sajjadio.trailers.data.dataSource.mapper

import com.sajjadio.trailers.data.dataSource.model.genre.GenreDto
import com.sajjadio.trailers.domain.model.GenresOfMovie

internal fun mapToGenresDomain(input: List<GenreDto>): List<GenresOfMovie> {
    return input.map {
        GenresOfMovie(
            id = it.id,
            name = it.name,
            imageUrl = getImageForGenreDomain(it.name.toString())
        )
    }
}

private fun getImageForGenreDomain(genreName: String): String {
    return when (genreName) {
        "Action" -> "https://th.bing.com/th/id/R.ba25910ca997ab51fdeea3a45f5dacb3?rik=iX2H5Mootb%2f3JA&pid=ImgRaw&r=0"
        "Adventure" -> "https://th.bing.com/th/id/OIP._s-eeBlZ1yKzJ5ZDfzw4lAHaEK?pid=ImgDet&rs=1"
        "Animation" -> "https://th.bing.com/th/id/R.7107ee0ee1f97f791bc6ffa9878b8ca4?rik=BqmyEFjidEoFqA&pid=ImgRaw&r=0"
        "Comedy" -> "https://th.bing.com/th/id/OIP.tLjJQDidFzWqoKAcSznHkgHaEK?pid=ImgDet&rs=1"
        "Crime" -> "https://th.bing.com/th/id/R.a627e6b97ec87c52273af9b4b3ddfc43?rik=5maURtsBWQfUPA&pid=ImgRaw&r=0"
        "Documentary" -> "https://th.bing.com/th/id/OIP.flYykItD6qn3bv9oXVBWKAHaEK?w=288&h=180&c=7&r=0&o=5&dpr=1.3&pid=1.7"
        "Drama" -> "https://th.bing.com/th/id/OIP.CuwRrr-rN08fB6ZF8Zzf2QHaEo?w=272&h=180&c=7&r=0&o=5&dpr=1.3&pid=1.7"
        "Family" -> "https://th.bing.com/th/id/OIP.sqZTXYuhlOkke8UNTV5k5wHaEK?pid=ImgDet&rs=1"
        "Fantasy" -> "https://th.bing.com/th/id/OIP.h3Mjni34u5LQFXBaFjOKqwHaEK?pid=ImgDet&rs=1"
        "History" -> "https://th.bing.com/th/id/OIP.nVgE5asuV4UpjNlUXnnXTQHaFj?pid=ImgDet&rs=1"
        "Horror" -> "https://th.bing.com/th/id/OIP.kPR-izo6VMxamvF4zCOTAgAAAA?pid=ImgDet&w=199&h=199&c=7&dpr=1.3"
        "Music" -> "https://th.bing.com/th/id/OIP.zD0hHtTrPNY335hGhVKkDAAAAA?pid=ImgDet&w=199&h=212&c=7&dpr=1.3"
        "Mystery" -> "https://th.bing.com/th/id/OIP.PqYsfH0oQwnXbSmOXjtrAwHaEK?w=305&h=180&c=7&r=0&o=5&dpr=1.3&pid=1.7"
        "Romance" -> "https://th.bing.com/th/id/OIP.5HjFgr5GFK-OScux6Hn8yAHaE3?pid=ImgDet&w=199&h=130&c=7&dpr=1.3"
        "Science Fiction" -> "https://th.bing.com/th/id/OIP.5KgJ0N29zYihLIKsdu5cFAHaDt?w=301&h=175&c=7&r=0&o=5&dpr=1.3&pid=1.7"
        "TV Movie" -> "https://th.bing.com/th/id/OIP.wDljt0Sw863MtSrfu6ec8gHaEK?w=263&h=180&c=7&r=0&o=5&dpr=1.3&pid=1.7"
        "Thriller" -> "https://th.bing.com/th/id/OIP.gZaq2u3QU6Z8ci7fP0oGzQHaFZ?pid=ImgDet&w=199&h=144&c=7&dpr=1.3"
        "War" -> "https://th.bing.com/th/id/OIP.Kq0iThh8QbB-VeqhKnPoOAHaEK?w=286&h=180&c=7&r=0&o=5&dpr=1.3&pid=1.7"
        else -> "https://th.bing.com/th/id/OIP.zTsC31d0TuT8o1qMYra5ZAHaEK?w=276&h=180&c=7&r=0&o=5&dpr=1.3&pid=1.7"
    }
}



