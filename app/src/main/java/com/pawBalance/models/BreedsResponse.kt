package com.pawBalance.models

data class BreedsResponse(
    val message: Map<String, List<String>>,
    val status: String
)