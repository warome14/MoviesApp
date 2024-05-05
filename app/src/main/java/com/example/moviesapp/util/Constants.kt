package com.example.moviesapp.util

import com.google.gson.Gson

object Constants {
    const val BASE_URL = "https://eldenring.fanapis.com/api/"
    const val API_BOSSES = "bosses"
}

fun <A> String.fromJson(type: Class<A>): A {
    return Gson().fromJson(this, type)
}

fun <A> A.toJson(): String? {
    return Gson().toJson(this)
}