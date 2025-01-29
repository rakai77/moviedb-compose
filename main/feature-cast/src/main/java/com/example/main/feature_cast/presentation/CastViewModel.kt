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
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
class CastViewModel(
    private val castUseCase: CastUseCase
) : ViewModel() {

    private val _query = MutableStateFlow("")
    val query = _query.asStateFlow()

    private val _castUiState = MutableStateFlow<PagingData<CastItem>>(PagingData.empty())
    val castUiState = _castUiState.asStateFlow()

    init {
        observeCast()
    }

    private fun observeCast() {
        viewModelScope.launch {
            _query.debounce(500)
                .distinctUntilChanged()
                .flatMapLatest { query ->
                    castUseCase.searchCast(query)
                }
                .cachedIn(viewModelScope)
                .collect {
                    _castUiState.value = it
                }
        }
    }

    fun setQuery(query: String) {
        _query.value = query
    }
}