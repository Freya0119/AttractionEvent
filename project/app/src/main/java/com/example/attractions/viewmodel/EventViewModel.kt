package com.example.attractions.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.example.attractions.ServiceApi
import com.example.attractions.data.Event
import com.example.attractions.data.EventUiState
import com.example.attractions.data.Events
import com.example.attractions.data.Link
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EventViewModel : ViewModel() {
    private var _eventList: MutableLiveData<List<Event>> = MutableLiveData()
    val uiState: LiveData<List<EventUiState>> by lazy {
        _eventList.map { list -> list.map { getEventUiState(it) } }
    }

    init {
        getEvents("en")
    }

    private fun getEventUiState(event: Event): EventUiState {
//        TODO: wrong
        Log.d("EVENT_LOG", "Get event item: $event")
        return if (event.links.isEmpty()) {
            EventUiState(event.description, listOf(Link("Src", "Subject")), event.title, event.url)
        } else {
            EventUiState(event.description, event.links, event.title, event.url)
        }
    }

    fun getEvents(lang: String) {
        val apiService = ServiceApi.api.getEvents(lang)
        apiService.enqueue(object : Callback<Events> {
            override fun onResponse(call: Call<Events>, response: Response<Events>) {
                if (response.isSuccessful) {
                    _eventList.value = response.body()?.events
                    Log.d("EVENT_LOG", "Get success. First event: ${uiState.value?.get(0)}")
                } else {
                    Log.d("EVENT_LOG", "Get fail!!! Error code is: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<Events>, t: Throwable) {
                Log.d("EVENT_LOG", "Get fail!!! Error: ${t.message}")
            }
        })
    }
}