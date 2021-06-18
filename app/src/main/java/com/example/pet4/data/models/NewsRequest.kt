package com.example.pet4.data.models




/**
 * Класс, в который парсится JSON запрос с API. Содержит данные по городам и доп. информацю.
 */
class NewsRequest {
    var message: String = ""
    var accurate: String = ""
    var cod: Long = 0
    var count: Long = 0
    var list: List<Place> = listOf()
}