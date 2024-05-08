package com.example.testtaskfromvk.ui.listproducts


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testtaskfromvk.data.productmodel.AllProducts
import com.example.testtaskfromvk.data.response.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val apiService: ApiService
) : ViewModel() {

    private val _liveData = MutableLiveData<AllProducts>()
    val liveData: LiveData<AllProducts> get() = _liveData


    suspend fun getProduct(pageInx: Int){
        val result = apiService.getProducts(20, pageInx*20)
        if (result.isSuccessful){
            _liveData.postValue(result.body())
        }
    }

    suspend fun getProductByCategory(category: String, pageInx: Int){
        val result = apiService.getProductsByCategory(category,20, pageInx*20)
        if (result.isSuccessful){
            _liveData.postValue(result.body())
        }
    }
}
