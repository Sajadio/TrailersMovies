package com.sajjadio.trailers.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.sajjadio.trailers.data.dataSource.local.AppDatabase
import com.sajjadio.trailers.data.dataSource.local.entites.SearchRemoteKey
import com.sajjadio.trailers.data.dataSource.mapper.mapSearchMovieEntity
import com.sajjadio.trailers.data.dataSource.remote.MovieApiService
import com.sajjadio.trailers.domain.model.SearchMovieResult

@ExperimentalPagingApi
class SearchRemoteMediator(
    private val api: MovieApiService,
    private val db: AppDatabase,
    private val query: String?,
) : RemoteMediator<Int, SearchMovieResult>() {

    private val searchDao = db.getMovieDao()
    private val searchKeyDao = db.getRemoteKeyDao()

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

            val response = api.getSearchMovie(page = currentPage, query = query)
            val data = response.body()?.results?.let {
                mapSearchMovieEntity(it)
            }?: emptyList()

            val endOfPaginationReached = data.isEmpty()

            val prevPage = if (currentPage == 1) null else currentPage - 1
            val nextPage = if (endOfPaginationReached) null else currentPage + 1

            db.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    searchDao.deleteAllSavedSearchMovies()
                    searchKeyDao.deleteAllRemoteKeys()
                }
                val keys = data.map { searchResult ->
                    SearchRemoteKey(
                        id = searchResult.id,
                        prevPage = prevPage,
                        nextPage = nextPage
                    )
                }
                searchKeyDao.addAllRemoteKeys(searchRemoteKeys = keys)
                searchDao.addSearchMovies(items = data)
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
                searchKeyDao.getRemoteKeys(id = id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, SearchMovieResult>
    ): SearchRemoteKey? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { searchResult ->
                searchKeyDao.getRemoteKeys(id = searchResult.id)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, SearchMovieResult>
    ): SearchRemoteKey? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { searchResult ->
                searchKeyDao.getRemoteKeys(id = searchResult.id)
            }
    }

}