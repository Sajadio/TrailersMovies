package com.example.trailers.ui.vm

import androidx.lifecycle.*
import com.example.trailers.data.repository.MDBRepo
import com.example.trailers.utils.ListHomeAdapterItem
import com.example.trailers.utils.NetworkStatus
import com.example.trailers.utils.ParentListAdapter
import com.example.trailers.utils.ViewTypeHome
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class MDBViewModel @Inject constructor(private val mdbRepo: MDBRepo) : ViewModel() {

    private val _trending: MutableLiveData<List<ListHomeAdapterItem<Any>>> =
        MutableLiveData()
    val trending: LiveData<List<ListHomeAdapterItem<Any>>> = _trending

    private val _status: MutableLiveData<NetworkStatus<ParentListAdapter>> =
        MutableLiveData()
    val status: LiveData<NetworkStatus<ParentListAdapter>> = _status

    init {
        initialNestedRecyclerView()
    }

    private fun initialNestedRecyclerView() {
        val list = mutableListOf<ListHomeAdapterItem<Any>>()

        viewModelScope.launch(Dispatchers.IO) {
            mdbRepo.getTrending().collectLatest {
                _status.postValue(it)
                it.data()?.let { response ->
                    list.add(ListHomeAdapterItem(response, ViewTypeHome.TREND))
                    list.add(ListHomeAdapterItem("", ViewTypeHome.VIEW_MORE_POPULAR))
                    list.add(ListHomeAdapterItem("", ViewTypeHome.POPULAR))
                    list.add(ListHomeAdapterItem("", ViewTypeHome.VIEW_MORE_TOP_RATED))
                    list.add(ListHomeAdapterItem("", ViewTypeHome.RATED))
                    list.add(ListHomeAdapterItem("", ViewTypeHome.VIEW_MORE_UPCOMING))
                    list.add(ListHomeAdapterItem("", ViewTypeHome.UPCOMING))
                }

            }
            _trending.postValue(list)


        }
    }

}
