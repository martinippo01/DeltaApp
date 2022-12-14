
package com.example.myapplication.data.network.model
import com.example.myapplication.data.model.User
import com.google.gson.annotations.SerializedName
import java.util.Date

class NetworkUser (

    @SerializedName("id")
    var id: Int,
   @SerializedName("username")
    var username: String,

  /*  @SerializedName("firstName")
    var firstName: String? = null,

    @SerializedName("lastName")
    var lastName: String? = null,

   @SerializedName("gender")
   var gender: String? = null,

   @SerializedName("birthdate")
   var birthdate: Int? = null,

  @SerializedName("email")
  var email: String,
  @SerializedName("phone")
  var phone: String? = null,
  @SerializedName("avatarUrl")
  var avatarUrl: String? = null,
  @SerializedName("metadata")
  var metadata: NetworkUserMetadata? = null,
  @SerializedName("date")
  var date: Date,*/
  @SerializedName("lastActivity")
  var lastActivity: Date? = null,
    @SerializedName("metadata")
    var metadata: NetworkUserMetadata? = null,
 /*@SerializedName("verified")
  var verified: Boolean*/
) {

    fun asModel() : User {
        return User(
            id = id,
           username = username,
            lastActivity = lastActivity,
            avatarUrl = metadata?.img
        )
    }
}

