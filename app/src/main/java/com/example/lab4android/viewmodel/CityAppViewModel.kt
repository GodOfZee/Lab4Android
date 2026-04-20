package com.example.lab4android.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.lab4android.data.PlaceCategory
import com.example.lab4android.data.Recommendation
import com.example.lab4android.data.Repository
import com.example.lab4android.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CityAppViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: Repository = Repository(application)

    private val _recommendations = MutableStateFlow<List<Recommendation>>(emptyList())
    val recommendations: StateFlow<List<Recommendation>> = _recommendations.asStateFlow()

    private val _selectedBottomTab = MutableStateFlow("home")
    val selectedBottomTab: StateFlow<String> = _selectedBottomTab.asStateFlow()

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val data = repository.loadData() ?: getDefaultRecommendations()
                _recommendations.value = data
            } catch (e: Exception) {
                _recommendations.value = getDefaultRecommendations()
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun getRecommendationsByCategory(category: String): List<Recommendation> {
        return _recommendations.value.filter { it.category.name == category }
    }

    fun getRecommendationById(id: Int): Recommendation? {
        return _recommendations.value.getOrNull(id)
    }

    fun updateSelectedTab(tab: String) {
        _selectedBottomTab.value = tab
    }

    private fun getDefaultRecommendations(): List<Recommendation> {
        return listOf(
            Recommendation(
                id = 0,
                name = R.string.coffee_1_name,
                address = R.string.coffee_1_address,
                description = R.string.coffee_1_description,
                image = R.drawable.ic_coffee,
                category = PlaceCategory.COFFEE
            ),
            Recommendation(
                id = 1,
                name = R.string.coffee_2_name,
                address = R.string.coffee_2_address,
                description = R.string.coffee_2_description,
                image = R.drawable.ic_coffee,
                category = PlaceCategory.COFFEE
            ),
            Recommendation(
                id = 2,
                name = R.string.park_1_name,
                address = R.string.park_1_address,
                description = R.string.park_1_description,
                image = R.drawable.ic_park,
                category = PlaceCategory.PARKS
            ),
            Recommendation(
                id = 3,
                name = R.string.park_2_name,
                address = R.string.park_2_address,
                description = R.string.park_2_description,
                image = R.drawable.ic_park,
                category = PlaceCategory.PARKS
            ),
            Recommendation(
                id = 4,
                name = R.string.restaurant_1_name,
                address = R.string.restaurant_1_address,
                description = R.string.restaurant_1_description,
                image = R.drawable.ic_restaurant,
                category = PlaceCategory.RESTAURANTS
            ),
            Recommendation(
                id = 5,
                name = R.string.museum_1_name,
                address = R.string.museum_1_address,
                description = R.string.museum_1_description,
                image = R.drawable.ic_museum,
                category = PlaceCategory.MUSEUMS
            )
        )
    }
}