package com.example.androiddemo.data

import androidx.annotation.DrawableRes
import com.example.androiddemo.R

/**
 * McLaren Car Data Model
 * 迈凯轮车型数据模型
 * 
 * Supports both local resources and network images:
 * - imageRes: Local drawable/mipmap resource ID (fallback)
 * - imageUrl: Network image URL (primary, loaded via Coil)
 * 
 * 同时支持本地资源和网络图片：
 * - imageRes: 本地 drawable/mipmap 资源 ID（备用）
 * - imageUrl: 网络图片 URL（主要，通过 Coil 加载）
 */
data class McLarenCar(
    val id: Int,
    val name: String,
    val series: String,
    val year: Int,
    val horsepower: Int,
    val acceleration: String,  // 0-100 km/h time
    val topSpeed: Int,         // km/h
    val price: String,
    @DrawableRes val imageRes: Int,  // Local fallback image
    val imageUrl: String,            // Network image URL
    val description: String,
    val isFeatured: Boolean = false
)

/**
 * Sample McLaren car data with network images
 * 迈凯轮车型示例数据（含网络图片）
 * 
 * Note: Using high-quality McLaren official images from Unsplash
 * 注意：使用来自 Unsplash 的高质量迈凯轮官方图片
 */
object McLarenData {
    
    // Base URL for placeholder images (using picsum for demo)
    // 占位图片基础 URL（演示使用 picsum）
    private const val UNSPLASH_BASE = "https://images.unsplash.com"
    
    val cars = listOf(
        McLarenCar(
            id = 1,
            name = "McLaren P1",
            series = "Ultimate Series",
            year = 2013,
            horsepower = 903,
            acceleration = "2.8s",
            topSpeed = 350,
            price = "$1.15M",
            imageRes = R.drawable.ic_launcher_foreground,
            imageUrl = "$UNSPLASH_BASE/photo-1544636331-e26879cd4d9b?w=800&q=80",
            description = "Hybrid hypercar with F1-derived technology",
            isFeatured = true
        ),
        McLarenCar(
            id = 2,
            name = "McLaren 720S",
            series = "Super Series",
            year = 2017,
            horsepower = 710,
            acceleration = "2.9s",
            topSpeed = 341,
            price = "$299K",
            imageRes = R.drawable.ic_launcher_foreground,
            imageUrl = "$UNSPLASH_BASE/photo-1621135802920-133df287f89c?w=800&q=80",
            description = "The benchmark in the supercar segment",
            isFeatured = false
        ),
        McLarenCar(
            id = 3,
            name = "McLaren Artura",
            series = "Super Series",
            year = 2021,
            horsepower = 671,
            acceleration = "3.0s",
            topSpeed = 330,
            price = "$237K",
            imageRes = R.drawable.ic_launcher_foreground,
            imageUrl = "$UNSPLASH_BASE/photo-1503376780353-7e6692767b70?w=800&q=80",
            description = "Next-generation hybrid supercar",
            isFeatured = true
        ),
        McLarenCar(
            id = 4,
            name = "McLaren 765LT",
            series = "Longtail",
            year = 2020,
            horsepower = 755,
            acceleration = "2.8s",
            topSpeed = 330,
            price = "$358K",
            imageRes = R.drawable.ic_launcher_foreground,
            imageUrl = "$UNSPLASH_BASE/photo-1542362567-b07e54358753?w=800&q=80",
            description = "Track-focused, limited production",
            isFeatured = false
        ),
        McLarenCar(
            id = 5,
            name = "McLaren Senna",
            series = "Ultimate Series",
            year = 2018,
            horsepower = 789,
            acceleration = "2.8s",
            topSpeed = 335,
            price = "$958K",
            imageRes = R.drawable.ic_launcher_foreground,
            imageUrl = "$UNSPLASH_BASE/photo-1558618666-fcd25c85cd64?w=800&q=80",
            description = "Ultimate track-concentrated road car",
            isFeatured = true
        ),
        McLarenCar(
            id = 6,
            name = "McLaren GT",
            series = "GT",
            year = 2019,
            horsepower = 612,
            acceleration = "3.2s",
            topSpeed = 326,
            price = "$210K",
            imageRes = R.drawable.ic_launcher_foreground,
            imageUrl = "$UNSPLASH_BASE/photo-1552519507-da3b142c6e3d?w=800&q=80",
            description = "Grand touring with supercar DNA",
            isFeatured = false
        ),
        McLarenCar(
            id = 7,
            name = "McLaren Elva",
            series = "Ultimate Series",
            year = 2020,
            horsepower = 804,
            acceleration = "2.8s",
            topSpeed = 327,
            price = "$1.69M",
            imageRes = R.drawable.ic_launcher_foreground,
            imageUrl = "$UNSPLASH_BASE/photo-1583121274602-3e2820c69888?w=800&q=80",
            description = "Open-cockpit, roofless roadster",
            isFeatured = true
        ),
        McLarenCar(
            id = 8,
            name = "McLaren Speedtail",
            series = "Ultimate Series",
            year = 2019,
            horsepower = 1035,
            acceleration = "2.5s",
            topSpeed = 403,
            price = "$2.25M",
            imageRes = R.drawable.ic_launcher_foreground,
            imageUrl = "$UNSPLASH_BASE/photo-1494976388531-d1058494cdd8?w=800&q=80",
            description = "Hyper-GT with central driving position",
            isFeatured = true
        )
    )
}
