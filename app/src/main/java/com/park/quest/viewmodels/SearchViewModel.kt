package com.park.quest.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.park.quest.database.Park
import com.park.quest.database.ParksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
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
    @ApplicationContext context: Context,
    private val parksRepository: ParksRepository
) : ViewModel() {
    private val _searchViewState: MutableStateFlow<List<Park>> =
        MutableStateFlow(emptyList())
    val searchViewState: StateFlow<List<Park>> = _searchViewState.asStateFlow()

    var parksCopy: List<Park> = emptyList()

    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                getSearchableParks()
            }
        }
    }

    private suspend fun getSearchableParks() {
        parksRepository.getParks().collect {
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