package com.example.main.feature_cast.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.core.domain.model.CastItem
import com.example.core.domain.usecase.CastUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
class CastViewModel(
    private val castUseCase: CastUseCase
) : ViewModel() {

    private val _query = MutableStateFlow("")
    val query = _query.asStateFlow()

    private val _popularCastState = MutableStateFlow<PagingData<CastItem>>(PagingData.empty())
    val popularCastState = _popularCastState.asStateFlow()

    private val _searchCastState = MutableStateFlow<PagingData<CastItem>>(PagingData.empty())
    val searchCastState = _searchCastState.asStateFlow()

    val isSearch = _query.map { it.isNotEmpty() }

    init {
        observePopularCast()
        observeSearch()
    }

    private fun observeSearch() {
        viewModelScope.launch {
            _query.debounce(500)
                .distinctUntilChanged()
                .flatMapLatest { query ->
                    if (query.isNotEmpty()) {
                        castUseCase.getCastFromSearch(query).cachedIn(viewModelScope)
                    } else {
                        flowOf(PagingData.empty())
                    }
                }
                .collect {
                    _searchCastState.value = it
                }
        }
    }

    fun setQuery(query: String) {
        _query.value = query
    }

    private fun observePopularCast() {
        viewModelScope.launch {
            castUseCase.getPopularCast()
                .cachedIn(viewModelScope)
                .collectLatest {
                    _popularCastState.value = it
                }
        }
    }
}