package com.example.attractions

import com.example.attractions.data.Attractions
import com.example.attractions.data.Events
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

const val BASE_URL = "https://www.travel.taipei/open-api/"
private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create()).build()

interface ServiceApi {
    @Headers("accept: application/json")
    @GET("{lang}/Attractions/All")
    fun getAttractions(@Path("lang") lang: String): Call<Attractions>

    @Headers("accept: application/json")
    @GET("{lang}/Events/News")
    fun getEvents(@Path("lang") lang: String): Call<Events>

    companion object {
        val api: ServiceApi by lazy {
            retrofit.create(ServiceApi::class.java)
        }
    }
}