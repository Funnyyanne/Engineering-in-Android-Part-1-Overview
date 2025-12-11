package com.example.androiddemo.model

/**
 * McLaren Car Model
 * Data class representing a McLaren supercar
 */
data class McLarenCar(
    val id: Int,
    val name: String,
    val series: String,
    val year: Int,
    val horsepower: Int,
    val topSpeed: Int,  // km/h
    val acceleration: Double,  // 0-100 km/h in seconds
    val price: String,
    val imageUrl: String,
    val description: String,
    val isFeatured: Boolean = false
)

/**
 * McLaren car data repository
 */
object McLarenRepository {
    
    val cars = listOf(
        McLarenCar(
            id = 1,
            name = "McLaren P1",
            series = "Ultimate Series",
            year = 2013,
            horsepower = 903,
            topSpeed = 350,
            acceleration = 2.8,
            price = "$1,150,000",
            imageUrl = "https://images.unsplash.com/photo-1621135802920-133df287f89c?w=800",
            description = "Hybrid hypercar with F1-derived technology",
            isFeatured = true
        ),
        McLarenCar(
            id = 2,
            name = "McLaren 720S",
            series = "Super Series",
            year = 2017,
            horsepower = 710,
            topSpeed = 341,
            acceleration = 2.9,
            price = "$299,000",
            imageUrl = "https://images.unsplash.com/photo-1603584173870-7f23fdae1b7a?w=800",
            description = "The benchmark supercar with prodigious performance",
            isFeatured = true
        ),
        McLarenCar(
            id = 3,
            name = "McLaren Artura",
            series = "Super Series",
            year = 2021,
            horsepower = 671,
            topSpeed = 330,
            acceleration = 3.0,
            price = "$237,500",
            imageUrl = "https://images.unsplash.com/photo-1618843479313-40f8afb4b4d8?w=800",
            description = "Next-generation High-Performance Hybrid",
            isFeatured = false
        ),
        McLarenCar(
            id = 4,
            name = "McLaren 750S",
            series = "Super Series",
            year = 2023,
            horsepower = 750,
            topSpeed = 332,
            acceleration = 2.8,
            price = "$324,000",
            imageUrl = "https://images.unsplash.com/photo-1544636331-e26879cd4d9b?w=800",
            description = "The most powerful, lightest Super Series ever",
            isFeatured = true
        ),
        McLarenCar(
            id = 5,
            name = "McLaren GT",
            series = "GT",
            year = 2019,
            horsepower = 612,
            topSpeed = 326,
            acceleration = 3.2,
            price = "$210,000",
            imageUrl = "https://images.unsplash.com/photo-1580274455191-1c62238fa333?w=800",
            description = "The Grand Tourer with supercar soul",
            isFeatured = false
        ),
        McLarenCar(
            id = 6,
            name = "McLaren Senna",
            series = "Ultimate Series",
            year = 2018,
            horsepower = 789,
            topSpeed = 335,
            acceleration = 2.8,
            price = "$958,966",
            imageUrl = "https://images.unsplash.com/photo-1552519507-da3b142c6e3d?w=800",
            description = "The ultimate track-focused road car",
            isFeatured = true
        ),
        McLarenCar(
            id = 7,
            name = "McLaren Speedtail",
            series = "Ultimate Series",
            year = 2019,
            horsepower = 1035,
            topSpeed = 403,
            acceleration = 2.9,
            price = "$2,250,000",
            imageUrl = "https://images.unsplash.com/photo-1503376780353-7e6692767b70?w=800",
            description = "Hyper-GT with central driving position",
            isFeatured = true
        ),
        McLarenCar(
            id = 8,
            name = "McLaren 600LT",
            series = "Longtail",
            year = 2018,
            horsepower = 592,
            topSpeed = 328,
            acceleration = 2.9,
            price = "$256,500",
            imageUrl = "https://images.unsplash.com/photo-1558618666-fcd25c85cd64?w=800",
            description = "Track-focused Longtail lightweight",
            isFeatured = false
        )
    )
    
    fun getFeaturedCars() = cars.filter { it.isFeatured }
    
    fun getCarsBySeries(series: String) = cars.filter { it.series == series }
    
    fun getAllSeries() = cars.map { it.series }.distinct()
}
