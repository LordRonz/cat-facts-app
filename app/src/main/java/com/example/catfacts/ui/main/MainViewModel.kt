package com.example.catfacts.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catfacts.network.CatFact
import com.example.catfacts.network.CatFactApi
import com.example.catfacts.network.CatImage
import com.example.catfacts.network.CatImageApi
import kotlinx.coroutines.launch

enum class CatApiStatus { LOADING, ERROR, DONE }

class MainViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    private val _status = MutableLiveData<CatApiStatus>()

    // The external immutable LiveData for the request status
    val status: LiveData<CatApiStatus> = _status
    private val _catFact = MutableLiveData<CatFact>()
    val catFact: LiveData<CatFact> = _catFact

    private val _catImage = MutableLiveData<CatImage>()
    val catImage: LiveData<CatImage> = _catImage

    init {
        getCatFact()
    }

    fun refresh() {
        getCatFact()
    }

    private fun getCatFact() {
        viewModelScope.launch {
            _status.value = CatApiStatus.LOADING
            try {
                _catFact.value = CatFactApi.retrofitService.getFact()
                _catImage.value = CatImageApi.retrofitService.getImage()[0]
                _status.value = CatApiStatus.DONE
            } catch (e: Exception) {
                _status.value = CatApiStatus.ERROR
            }
        }
    }

    private fun getCatImage() {
        viewModelScope.launch {
            _status.value = CatApiStatus.LOADING
            try {
                _catImage.value = CatImageApi.retrofitService.getImage()[0]
                _status.value = CatApiStatus.DONE
            } catch (e: Exception) {
                _status.value = CatApiStatus.ERROR
            }
        }
    }
}