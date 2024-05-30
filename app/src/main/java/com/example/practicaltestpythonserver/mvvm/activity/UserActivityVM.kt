package com.example.practicaltestpythonserver.mvvm.activity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practicaltestpythonserver.MyApplication
import com.example.practicaltestpythonserver.network.ApiService
import com.example.practicaltestpythonserver.network.NetworkSDK
import com.example.practicaltestpythonserver.network.request.LoginRequest
import com.example.practicaltestpythonserver.network.response.ApiResponse
import com.example.practicaltestpythonserver.network.response.handleLoginResponse

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import javax.inject.Inject

@HiltViewModel
class userActivityVM @Inject constructor(
    val myApplication: MyApplication,
    private val apiService: ApiService,
    private val networkSDK: NetworkSDK,
) : ViewModel() {

    var statusCode = MutableLiveData<Int>()


    fun login1(email: String, password: String) {

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val loginRequest = LoginRequest(email, password)
                val call: Call<Void> = apiService.login(loginRequest)
                val response = call.execute() // Synchronous call

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        // Update UI or perform any other action
                        statusCode.postValue(200)
                    } else {
                        //Error handling remain
                    }
                }
            } catch (e: Exception) {
                // Handle exception
                println("RES-->Exception occurred: ${e.message}")
                e.printStackTrace()
            }
        }
    }

    fun register(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val loginRequest = LoginRequest(email, password)
                val call: Call<Void> = apiService.register(loginRequest)
                val response = call.execute() // Synchronous call

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        // Update UI or perform any other action
                        println("RES--> successful response: ")
                        statusCode.postValue(200)
                    } else {
                        // Handle unsuccessful response
                        println("RES-->Unsuccessful response: ${response.code()}")
                    }
                }
            } catch (e: Exception) {
                // Handle exception
                println("RES-->Exception occurred: ${e.message}")
                e.printStackTrace()
            }
        }
    }



}



