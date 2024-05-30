package com.example.practicaltestpythonserver.network.response

import retrofit2.Call

sealed class ApiResponse {
    object Success : ApiResponse()
    data class Error(val code: Int, val message: String) : ApiResponse()
}

fun Call<Void>.handleLoginResponse(): ApiResponse {
    return try {
        val response = execute()
        if (response.isSuccessful) {
            ApiResponse.Success
        } else {
            ApiResponse.Error(response.code(), response.errorBody()?.string() ?: "Unknown error")
        }
    } catch (e: Exception) {
        ApiResponse.Error(-1, e.message ?: "Unknown error")
    }
}