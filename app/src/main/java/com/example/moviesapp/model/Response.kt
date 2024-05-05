package com.example.moviesapp.model

import android.graphics.Region


 data class Boss(var id: String
, val name: String
, val image: String
, val region: String
, val description: String
, val location: String
, val drops: List<String>
, val healthPoints: String) : java.io.Serializable

data class Response<T>(val success: String
                        , val count: Int
                        , val total: Int
                        , val data: List<T>)