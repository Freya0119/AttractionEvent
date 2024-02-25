package com.example.attractions.data

data class AttractionDetailState(
    val images: List<Image>,
    val introduction: String,
    val links: List<Any>,
    val name: String,
    val url: String,
) {
}