package com.example.practicaltestpythonserver.network



import com.example.practicaltestpythonserver.mvvm.dataModel.respose
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class NetworkSDK @Inject constructor(private val apiService: ApiService) {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://www.jsonkeeper.com/b/") // Base URL
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    @Throws(IOException::class)
    suspend fun fetchLatestMovies(apiKey: String): String {
//        return apiService.getLatestMovies(apiKey).string()
        return "HI VIJAY How are you?"
    }
    @Throws(IOException::class)
    suspend fun getScreens(): Call<respose> {
         return   apiService.getScreens()

    }


}