package com.zainco.library.annotations

import com.google.gson.GsonBuilder
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

fun main() {
    val user = User("Ahmed", 30,156)
    /* @Expose won't affect when using Gson() only but use
         val gson = GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
     instead*/

    val gson = GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
    val jsonString = gson.toJson(user)
    println("Serialized \n $jsonString\n")

    val json =
        "{\"age\":30,\"name\":\"John\"}"

    val Deuser: User = gson.fromJson<User>(json, User::class.java)

    println("DeSerialized \n name: ${Deuser.userName} age: ${Deuser.userAge}")
}

class User(
    @field:SerializedName("name") @Expose(serialize = false) val userName: String,
    @field:SerializedName("age") @Expose(deserialize = false) val userAge: Int,
    val zain: Int
)

/*
Serialized
 {"age":30}

DeSerialized
 name: John age: 0

 */
/*
////////////////////////////
fun main() {
    val user = User("Ahmed", 30)
    *//*@Expose won't affect when using Gson() only but use
        val gson = GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
    instead
     *//*
    val gson = Gson()
    val jsonString = gson.toJson(user)
    println("Serialized \n $jsonString\n")

    val json =
        "{\"age\":30,\"name\":\"John\"}"

    val Deuser: User = gson.fromJson<User>(json, User::class.java)

    println("DeSerialized \n name: ${Deuser.userName} age: ${Deuser.userAge}")
}*/
/*
Serialized
 {"name":"Ahmed","age":30}

DeSerialized
 name: John age: 30
 */