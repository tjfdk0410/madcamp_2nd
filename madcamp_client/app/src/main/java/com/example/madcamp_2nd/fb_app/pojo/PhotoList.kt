package com.example.madcamp_2nd.fb_app.pojo


import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName
import org.json.JSONArray
import org.json.JSONObject
import java.util.*
import kotlin.collections.ArrayList

class PhotoList { lateinit var array: ArrayList<JSONObject>}

//@SerializedName("page")
//var page: Int? = null
//@SerializedName("per_page")
//var perPage: Int? = null
//@SerializedName("total")
//var total: Int? = null
//@SerializedName("total_pages")
//var totalPages: Int? = null
//@SerializedName("data")
//var data: ArrayList<Any?> = ArrayList<Any?>()
//
//inner class Datum {
//    @SerializedName("id")
//    var id: Int? = null
//    @SerializedName("first_name")
//    var first_name: String? = null
//    @SerializedName("last_name")
//    var last_name: String? = null
//    @SerializedName("avatar")
//    var avatar: String? = null
//}