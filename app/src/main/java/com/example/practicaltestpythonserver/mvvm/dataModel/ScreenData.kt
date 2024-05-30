package com.example.practicaltestpythonserver.mvvm.dataModel

data class respose(
    val screens: List<screenData>
)


data class screenData(
    val id: Int,
    val name: String,
    val total_seats: Int
)

data class seatsResponse(
    val screen_id: Int,
    val seat_layout: List<seatData>
)
data class seatData(
    val row: Int,
    val price: Int,
    val seats: List<String>,
    val isBooked:Boolean=false
)


