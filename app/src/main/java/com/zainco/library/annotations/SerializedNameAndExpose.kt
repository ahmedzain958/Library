package com.zainco.library.annotations

import com.google.gson.GsonBuilder
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


fun main() {
    val user = User("Ahmed", 30)
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
    @field:SerializedName("age") @Expose(deserialize = false) val userAge: Int
)