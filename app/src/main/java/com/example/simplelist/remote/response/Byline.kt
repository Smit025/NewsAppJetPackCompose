package com.example.simplelist.remote.response

data class Byline(
    val organization: Any,
    val original: String,
    val person: List<Person>
)