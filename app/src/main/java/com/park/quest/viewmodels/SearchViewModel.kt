package com.park.quest.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.park.quest.database.Park
import com.park.quest.database.PassportRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by Nirbhay Pherwani on 7/25/2023.
 */
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val passportRepository: PassportRepository
) : ViewModel() {
    private val _searchViewState: MutableStateFlow<List<Park>> =
        MutableStateFlow(emptyList())
    val searchViewState: StateFlow<List<Park>> = _searchViewState.asStateFlow()

    private var parksCopy: List<Park> = emptyList()

    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                getSearchableParks()
            }
        }
    }

    private suspend fun getSearchableParks() {
        passportRepository.getParks().collect {
            _searchViewState.value = it
            parksCopy = it
        }
    }

    fun searchParks(query: String) {
        _searchViewState.update {
            parksCopy.filter { it.name.lowercase().contains(query) }
        }
    }

}