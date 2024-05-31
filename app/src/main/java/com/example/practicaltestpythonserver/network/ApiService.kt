package com.example.practicaltestpythonserver.network

import com.example.practicaltestpythonserver.mvvm.dataModel.InspectionResponse
import com.example.practicaltestpythonserver.mvvm.dataModel.respose
import com.example.practicaltestpythonserver.mvvm.dataModel.seatsResponse
import com.example.practicaltestpythonserver.network.request.LoginRequest
import com.example.practicaltestpythonserver.network.response.LoginSuccessResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {

    @POST("api/login")
    suspend fun login1(@Body loginRequest: LoginRequest): Call<Void>

    @POST("api/login")
    fun login(@Body loginRequest: LoginRequest): Call<Void>

    @POST("api/register")
    fun register(@Body loginRequest: LoginRequest): Call<Void>

    @GET("api/inspections/start")
    fun getQuestions(): Call<InspectionResponse>

    @POST("api/inspections/submit")
    fun submitAnswers(@Body inspectionResponse: InspectionResponse): Call<Void>
}