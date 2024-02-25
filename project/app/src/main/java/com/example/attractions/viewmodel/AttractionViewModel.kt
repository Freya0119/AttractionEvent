package com.example.attractions.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.example.attractions.ServiceApi
import com.example.attractions.data.Attraction
import com.example.attractions.data.AttractionDetailState
import com.example.attractions.data.AttractionUiState
import com.example.attractions.data.Attractions
import com.example.attractions.data.Event
import com.example.attractions.data.EventUiState
import com.example.attractions.data.Events
import com.example.attractions.data.Link
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val VIEW_MODEL_MSG = "VIEW_MODEL_MSG"

class AttractionViewModel : ViewModel() {
    private val _liveDataList: MutableLiveData<List<Any>> = MutableLiveData<List<Any>>()
    val uiStateList: LiveData<List<Any>> by lazy {
        _liveDataList.map { list ->
            list.map {
                when (it) {
                    is Attraction -> {
                        Log.d(VIEW_MODEL_MSG, "It is attraction.")
                        getAttractionUiState(it)
                    }

                    is Event -> {
                        Log.d(VIEW_MODEL_MSG, "It is event.")
//                        getEventUiState(it)
                    }

                    else -> {
                        Log.d(VIEW_MODEL_MSG, "Unknown data type!")
                    }
                }
            }
        }
    }

    init {
        getAttractions("zh-tw")
    }

//    fun setAttraction(name: String): AttractionDetailState {
//        return _liveDataList.value?.find { it.name == name }!!.let {
//            AttractionDetailState(it.images, it.introduction, it.links, it.name, it.url)
//        }
//    }

    private fun getAttractionUiState(attraction: Attraction): AttractionUiState {
        return if (attraction.images.isEmpty()) {
//            TODO: "No image" 後面需要判斷 image 是否正確
            AttractionUiState("No image", attraction.introduction, attraction.name)
        } else {
            AttractionUiState(attraction.images[0].src, attraction.introduction, attraction.name)
        }
    }

    //    TODO: =ServiceApi.api.getAttractions()
    fun getAttractions(lang: String) {
//        TODO: apiService where
        val apiService = ServiceApi.api.getAttractions(lang)
        apiService.enqueue(object : Callback<Attractions> {
            override fun onResponse(call: Call<Attractions>, response: Response<Attractions>) {
                if (response.isSuccessful) {
                    _liveDataList.value = response.body()?.attractions
                    Log.d(VIEW_MODEL_MSG, "Get success. First attraction: ${uiStateList.value?.get(0)}")
                } else {
                    Log.d(VIEW_MODEL_MSG, "Get fail!!! Error code is: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<Attractions>, t: Throwable) {
                Log.d(VIEW_MODEL_MSG, "Get fail!!! Error: ${t.message}")
            }
        })
    }
}

//  TODO: for scope.launch{}
//class MainViewModel: ViewModel() {
//    fun getForecast(country: String) {
//        val service = WeathbyRetrofit.makeRetrofitService()
//        viewModelScope.launch {
//            runCatching {
//                service.getForecast(query = country)
//            }.onSuccess {
//                Log.i("success", "onCreate: $it")
//            }.onFailure {
//                Log.i("fail", "onCreate: $it")
//            }
//        }
//    }
//}