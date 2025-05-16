package com.example.mysecondapplication

import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import com.example.mysecondapplication.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FavoritesViewModel : ViewModel() {
    private val _favorites = MutableStateFlow<List<Product>>(emptyList())
    val favorites: StateFlow<List<Product>> = _favorites

    fun addFavorite(product: Product) {
        if (!_favorites.value.contains(product)) {
            _favorites.value = _favorites.value + product
        }
    }
}
