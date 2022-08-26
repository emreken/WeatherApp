package com.ekenapp.weatherapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ekenapp.weatherapp.api.Repository
import com.ekenapp.weatherapp.api.model.SearchRecommendation
import kotlinx.coroutines.launch

class SearchViewModel(private val repository: Repository) : ViewModel() {
    val searchResults: MutableLiveData<List<SearchRecommendation>?> = MutableLiveData<List<SearchRecommendation>?>()

    fun getSearchResults(userText: String) {
        viewModelScope.launch {
            val result = try {
                repository.getSearchRecommendations(userText)
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
            searchResults.value = result
        }
    }
}