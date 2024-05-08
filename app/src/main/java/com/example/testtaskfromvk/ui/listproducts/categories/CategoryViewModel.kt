package com.example.testtaskfromvk.ui.listproducts.categories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testtaskfromvk.data.response.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val apiService: ApiService
):ViewModel() {

    private val _liveData = MutableLiveData<List<String>>()
    val liveData: LiveData<List<String>> = _liveData

    suspend fun getCategories(){
        val result = apiService.getCategories()
        if (result.isSuccessful){
            _liveData.postValue(result.body())
        }
    }
}