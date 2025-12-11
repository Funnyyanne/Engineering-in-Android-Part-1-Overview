package com.example.androiddemo.xml

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.androiddemo.R
import com.example.androiddemo.data.McLarenCar
import com.example.androiddemo.databinding.ItemMclarenCarBinding

/**
 * McLaren RecyclerView Adapter with Coil Image Loading
 * 使用 Coil 加载图片的迈凯轮 RecyclerView 适配器
 */
class McLarenAdapter(
    private val cars: List<McLarenCar>,
    private val onItemClick: (McLarenCar) -> Unit
) : RecyclerView.Adapter<McLarenAdapter.McLarenViewHolder>() {

    private val cardHeights = listOf(280, 220, 250)

    class McLarenViewHolder(
        val binding: ItemMclarenCarBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): McLarenViewHolder {
        val binding = ItemMclarenCarBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return McLarenViewHolder(binding)
    }

    override fun onBindViewHolder(holder: McLarenViewHolder, position: Int) {
        val car = cars[position]

        holder.binding.apply {
            // ============================================================
            // Load network image using Coil
            // 使用 Coil 加载网络图片
            // ============================================================
            // Coil provides extension function: imageView.load(url)
            // Features: crossfade, placeholder, error
            // ============================================================
            carImage.load(car.imageUrl) {
                crossfade(true)
                crossfade(300)
                placeholder(R.drawable.ic_launcher_foreground)
                error(car.imageRes)
            }

            // Set car information - matching Compose version
            // 设置车辆信息 - 与 Compose 版本一致
            carName.text = car.name
            seriesBadge.text = car.series
            yearBadge.text = car.year.toString()
            carPrice.text = car.price

            // Combined specs text like Compose version
            // 组合规格文本，与 Compose 版本一致
            specsText.text = "${car.horsepower} HP • ${car.acceleration} • ${car.topSpeed} km/h"

            // Show/hide featured badge
            // 显示/隐藏精选标识
            featuredBadge.visibility = if (car.isFeatured) View.VISIBLE else View.GONE

            // Vary card height for staggered effect - 与 Compose 版本一致
            // 变化卡片高度以产生瀑布流效果
            // Compose uses: when (car.id % 3) { 0 -> 280.dp, 1 -> 220.dp, else -> 250.dp }
            val heightDp = cardHeights[car.id % 3]
            val density = holder.itemView.context.resources.displayMetrics.density
            val heightPx = (heightDp * density).toInt()
            val params = carImage.layoutParams
            params.height = heightPx
            carImage.layoutParams = params

            root.setOnClickListener { onItemClick(car) }
        }
    }

    override fun getItemCount(): Int = cars.size
}