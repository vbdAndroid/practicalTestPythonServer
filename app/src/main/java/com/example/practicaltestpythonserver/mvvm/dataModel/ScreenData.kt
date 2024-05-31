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


data class InspectionResponse(
    val inspection: Inspection
)

data class Inspection(
    val area: Area,
    val id: Int,
    val inspectionType: InspectionType,
    val survey: Survey
)

data class Area(
    val id: Int,
    val name: String
)

data class InspectionType(
    val access: String,
    val id: Int,
    val name: String
)

data class Survey(
    val categories: List<Category>,
    val id: Int
)

data class Category(
    val id: Int,
    val name: String,
    val questions: List<Question>
)

data class Question(
    val answerChoices: List<AnswerChoice>,
    val id: Int,
    val name: String,
    var selectedAnswerChoiceId: Int? = null
)

data class AnswerChoice(
    val id: Int,
    val name: String,
    val score: Double
)


