package com.example.core.data.remote

object HttpRoute {
    // list
    const val ALL_TRENDING = "trending/all/week"
    const val MOVIE_POPULAR = "movie/popular"
    const val ON_AIR_SERIES = "tv/on_the_air"
    const val POPLAR_CAST = "person/popular"
    const val SEARCH_CAST = "search/person"

    // detail
    const val MOVIE_DETAIL = "movie/{movie_id}"
    const val SERIES_DETAIL = "tv/{series_id}"

    //credits
    const val MOVIE_CREDITS = "movie/{movie_id}/credits"
}