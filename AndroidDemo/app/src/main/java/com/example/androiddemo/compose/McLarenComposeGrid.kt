package com.example.androiddemo.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.androiddemo.R
import com.example.androiddemo.data.McLarenCar
import com.example.androiddemo.data.McLarenData

/**
 * McLaren Staggered Grid - Compose Implementation
 * 
 * Demonstrates:
 * - LazyVerticalStaggeredGrid for waterfall/masonry layout
 * - Coil AsyncImage for network image loading
 * - Compose's declarative UI approach
 * - State management with remember
 * - Material 3 design components
 * 
 * 演示内容：
 * - LazyVerticalStaggeredGrid 实现瀑布流布局
 * - Coil AsyncImage 加载网络图片
 * - Compose 声明式 UI 方法
 * - 使用 remember 进行状态管理
 * - Material 3 设计组件
 */
@Composable
fun McLarenComposeGrid() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        // Staggered Grid - Waterfall Layout
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalItemSpacing = 8.dp,
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF121212))
        ) {
            items(McLarenData.cars) { car ->
                McLarenCarCard(car = car)
            }
        }
    }
}

/**
 * McLaren Car Card with Coil Image Loading
 *
 * Coil Benefits / Coil 优势:
 * - Automatic memory & disk caching / 自动内存和磁盘缓存
 * - Crossfade animations / 淡入淡出动画
 * - Placeholder & error handling / 占位图和错误处理
 * - Coroutine-based, lifecycle aware / 基于协程，生命周期感知
 */
@Composable
private fun McLarenCarCard(car: McLarenCar) {
    // Varying heights for staggered effect
    // 变化高度以产生瀑布流效果
    val cardHeight = when (car.id % 3) {
        0 -> 280.dp
        1 -> 220.dp
        else -> 250.dp
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(cardHeight),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF1E1E1E)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            // Car Image - Using Coil AsyncImage for network loading
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(car.imageUrl)
                    .crossfade(true)  // Enable crossfade animation
                    .build(),
                contentDescription = car.name,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.ic_launcher_foreground),
                error = painterResource(id = car.imageRes)  // Fallback to local resource
            )

            // Gradient Overlay
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.8f)
                            ),
                            startY = 100f
                        )
                    )
            )

            // Car Info
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(12.dp)
            ) {
                // Featured Badge (if applicable)
                if (car.isFeatured) {
                    Surface(
                        shape = RoundedCornerShape(4.dp),
                        color = Color(0xFFFFD700)  // Gold color
                    ) {
                        Text(
                            text = "FEATURED",
                            fontSize = 8.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                }
                
                // Series Badge
                Surface(
                    shape = RoundedCornerShape(4.dp),
                    color = Color(0xFFFF6B00)
                ) {
                    Text(
                        text = car.series,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = car.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                // Performance specs: HP, 0-100, Top Speed
                // 性能参数: 马力、百公里加速、最高时速
                Text(
                    text = "${car.horsepower} HP • ${car.acceleration} • ${car.topSpeed} km/h",
                    fontSize = 11.sp,
                    color = Color(0xFFB0B0B0)
                )
                
                Text(
                    text = car.price,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFFF6B00)
                )
            }

            // Year Badge
            Surface(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(8.dp),
                shape = RoundedCornerShape(8.dp),
                color = Color.Black.copy(alpha = 0.6f)
            ) {
                Text(
                    text = car.year.toString(),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }
        }
    }
}
