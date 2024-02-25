package com.example.attractions.data

import com.google.gson.annotations.SerializedName

class Attractions {
    @SerializedName("data")
    lateinit var attractions: List<Attraction>
}

data class Attraction(
    val address: String,
    val category: List<Category>,
    val distric: String,
    val elong: Double,
    val email: String,
    val facebook: String,
    val fax: String,
    val files: List<Any>,
    val friendly: List<Any>,
    val id: Int,
    val images: List<Image>,
    val introduction: String,
    val links: List<Any>,
    val modified: String,
    val months: String,
    var name: String,
    val name_zh: Any,
    val nlat: Double,
    val official_site: String,
    val open_status: Int,
    val open_time: String,
    val remind: String,
    val service: List<Any>,
    val staytime: String,
    val target: List<Any>,
    val tel: String,
    val ticket: String,
    val url: String,
    val zipcode: String
)

data class Category(
    val id: Int,
    val name: String
)

data class Image(
    val ext: String,
    val src: String,
    val subject: String
)