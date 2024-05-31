package com.example.tourtle.data.api.retrofit

import com.example.tourtle.data.api.response.LoginResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface ApiService {

//    @FormUrlEncoded
//    @POST("register")
//    suspend fun register(
//        @Field("name") name: String,
//        @Field("email") email: String,
//        @Field("password") password: String
//    ): RegisterResponse

    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse

//    @GET("stories")
//    fun getStories(
//        @Header("Authorization") token: String,
//    ): Call<StoryResponse>
//
//    @GET("stories/{id}")
//    fun getDetail(
//        @Header("Authorization") token: String,
//        @Path("id") id: String,
//    ): Call<DetailResponse>
//
//    @Multipart
//    @POST("stories")
//    suspend fun uploadStory(
//        @Header("Authorization") token: String,
//        @Part("description") description: RequestBody,
//        @Part file: MultipartBody.Part,
//    ): UploadResponse
}