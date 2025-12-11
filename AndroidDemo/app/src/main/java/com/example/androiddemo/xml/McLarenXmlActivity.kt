package com.example.androiddemo.xml

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import coil.load
import com.example.androiddemo.R
import com.example.androiddemo.data.McLarenCar
import com.example.androiddemo.data.McLarenData
import com.example.androiddemo.databinding.ActivityMclarenXmlBinding
import com.example.androiddemo.databinding.ItemMclarenCarBinding

/**
 * McLaren XML Activity - Staggered Grid Implementation with Coil
 * 迈凯轮 XML 实现 - 瀑布流布局 + Coil 图片加载
 *
 * This activity demonstrates:
 * - RecyclerView with StaggeredGridLayoutManager for waterfall layout
 * - ViewBinding for type-safe view access
 * - Coil library for network image loading
 * - Adapter pattern with ViewHolder
 *
 * 本 Activity 演示：
 * - RecyclerView + StaggeredGridLayoutManager 实现瀑布流
 * - ViewBinding 类型安全视图访问
 * - Coil 库加载网络图片
 * - 适配器模式 + ViewHolder
 *
 * XML vs Compose Comparison / XML 与 Compose 对比:
 * ┌─────────────────┬─────────────────────┬─────────────────────┐
 * │ Feature         │ XML                 │ Compose             │
 * ├─────────────────┼─────────────────────┼─────────────────────┤
 * │ Layout          │ XML files           │ Kotlin DSL          │
 * │ View Access     │ ViewBinding/findBy  │ Direct in function  │
 * │ List            │ RecyclerView+Adapt. │ LazyColumn/Grid     │
 * │ Image Loading   │ coil.load()         │ AsyncImage()        │
 * │ State           │ LiveData/Flow       │ remember/State      │
 * │ Boilerplate     │ High                │ Low                 │
 * └─────────────────┴─────────────────────┴─────────────────────┘
 */
class McLarenXmlActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMclarenXmlBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize ViewBinding
        binding = ActivityMclarenXmlBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup Toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = "McLaren Collection"
            subtitle = "XML • RecyclerView • Coil"
        }

        // Handle back navigation
        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        setupRecyclerView()
    }

    /**
     * Configures RecyclerView with StaggeredGridLayoutManager
     * 配置 RecyclerView 使用 StaggeredGridLayoutManager
     */
    private fun setupRecyclerView() {
        val layoutManager = StaggeredGridLayoutManager(
            2,  // spanCount: 2 columns
            StaggeredGridLayoutManager.VERTICAL
        ).apply {
            gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
        }

        binding.recyclerViewMcLaren.apply {
            this.layoutManager = layoutManager
            
            // 添加间距 - 与 Compose 的 horizontalArrangement.spacedBy(8.dp) 和 verticalItemSpacing = 8.dp 一致
            addItemDecoration(GridSpacingItemDecoration(
                spanCount = 2,
                spacingDp = 8,
                context = this@McLarenXmlActivity
            ))
            
            adapter = McLarenAdapter(McLarenData.cars) { car ->
                Toast.makeText(
                    this@McLarenXmlActivity,
                    "Selected: ${car.name}\n${car.horsepower} HP • ${car.acceleration}",
                    Toast.LENGTH_SHORT
                ).show()
            }
            setHasFixedSize(false)
        }
    }
}




