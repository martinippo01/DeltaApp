package com.example.myapplication.data.network.model

import com.example.myapplication.ui.classes.Routines
import com.google.gson.annotations.SerializedName
import java.util.*

data class NetworkRoutine (

    @SerializedName("id")
    var id: Int,

    @SerializedName("name")
    var name: String,

    @SerializedName("detail")
    var detail: String,

    @SerializedName("date")
    var date : Date,

    @SerializedName("score")
    var score : Int,

    @SerializedName("isPublic")
    var isPublic : Boolean,

    @SerializedName("difficulty")
    var difficulty : String? = null,

    @SerializedName("user")
    var user : NetworkUserLite,

    @SerializedName("category")
    var category: NetworkCategory? = null,

    @SerializedName("metadata")
    var metadata: NetworkRoutineMetadata?=null
){
    fun asModel() : Routines {
        return Routines(
            id = id,
            img = metadata?.img ?: "",
            description = detail,
            title = name,
            points = score
        )
    }
}

