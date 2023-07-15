package com.sajjadio.trailers.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.sajjadio.trailers.data.base.MovieLocalDataSource
import com.sajjadio.trailers.data.base.MovieRemoteDataSource
import com.sajjadio.trailers.data.base.SearchMovieLocalDataSource
import com.sajjadio.trailers.data.dataSource.local.AppDatabase
import com.sajjadio.trailers.data.dataSource.local.entites.SearchRemoteKey
import com.sajjadio.trailers.data.dataSource.mapper.mapSearchMovieEntity
import com.sajjadio.trailers.domain.model.SearchMovieResult

@ExperimentalPagingApi
class SearchRemoteMediator(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val db: AppDatabase,
    private val query: String?,
    private val searchMovieLocalDataSource: SearchMovieLocalDataSource,
    private val movieLocalDataSource: MovieLocalDataSource,
) : RemoteMediator<Int, SearchMovieResult>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, SearchMovieResult>
    ): MediatorResult {
        return try {
            val currentPage = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    prevPage
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    nextPage
                }
            }

            val response = movieRemoteDataSource.getSearchMovie(page = currentPage, query = query)
            val data = response.results?.let {
                mapSearchMovieEntity(it)
            }?: emptyList()

            val endOfPaginationReached = data.isEmpty()

            val prevPage = if (currentPage == 1) null else currentPage - 1
            val nextPage = if (endOfPaginationReached) null else currentPage + 1

            db.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    movieLocalDataSource.deleteAllSavedSearchMovies()
                    searchMovieLocalDataSource.deleteAllRemoteKeys()
                }
                val keys = data.map { searchResult ->
                    SearchRemoteKey(
                        id = searchResult.id,
                        prevPage = prevPage,
                        nextPage = nextPage
                    )
                }
                movieLocalDataSource.addSearchMovies(items = data)
                searchMovieLocalDataSource.addAllRemoteKeys(searchRemoteKeys = keys)
            }
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, SearchMovieResult>
    ): SearchRemoteKey? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                searchMovieLocalDataSource.getRemoteKeys(id = id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, SearchMovieResult>
    ): SearchRemoteKey? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { searchResult ->
                searchMovieLocalDataSource.getRemoteKeys(id = searchResult.id)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, SearchMovieResult>
    ): SearchRemoteKey? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { searchResult ->
                searchMovieLocalDataSource.getRemoteKeys(id = searchResult.id)
            }
    }

}