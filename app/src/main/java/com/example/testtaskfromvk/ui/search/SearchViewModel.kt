package com.example.testtaskfromvk.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testtaskfromvk.data.productmodel.AllProducts
import com.example.testtaskfromvk.data.productmodel.Product
import com.example.testtaskfromvk.data.response.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val apiService: ApiService
): ViewModel() {

    private val _liveData = MutableLiveData<AllProducts>()
    val liveData: LiveData<AllProducts> = _liveData

    suspend fun searchProduct(query: String) {
        val result = apiService.searchProducts(query)
        if (result.isSuccessful){
            _liveData.postValue(result.body())
        }
    }
}