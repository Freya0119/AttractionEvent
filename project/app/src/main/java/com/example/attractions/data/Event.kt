package com.example.attractions.data

import com.google.gson.annotations.SerializedName


class Events {
    @SerializedName("data")
    lateinit var events: List<Event>
}
data class Event(
    val begin: Any,
    val description: String,
    val end: Any,
    val files: List<Any>,
    val id: Int,
    val links: List<Link>,
    val modified: String,
    val posted: String,
    val title: String,
    val url: String
)

data class Link(
    val src: String,
    val subject: String
)