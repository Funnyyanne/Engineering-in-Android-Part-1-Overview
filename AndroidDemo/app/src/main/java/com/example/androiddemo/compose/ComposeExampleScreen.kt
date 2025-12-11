package com.example.androiddemo.compose

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Compose Example Screen
 * 
 * Demonstrates Jetpack Compose advantages:
 * 1. Declarative UI - Describe "what" not "how"
 * 2. State-driven - State changes trigger automatic recomposition
 * 3. Kotlin-native - No XML needed
 * 4. Composable - Build complex UIs from simple components
 */
@Composable
fun ComposeExampleScreen() {
    // State management - use remember to preserve state
    // When text changes, Composables reading it will recompose
    var text by remember { mutableStateOf("Hello World!") }
    var counter by remember { mutableIntStateOf(0) }

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Title
            Text(
                text = "Compose Demo",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            // Hello World text - state changes auto-update UI
            Text(
                text = text,
                fontSize = 18.sp
            )

            // Button click - modifying state triggers UI update
            Button(
                onClick = { text = "Button Clicked!" }
            ) {
                Text("Click Me")
            }

            HorizontalDivider()

            // Counter example
            Text(
                text = "Counter Example",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            // 计数器显示
            Text(
                text = "Counter: $counter",
                fontSize = 24.sp
            )

            // 按钮行
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Button(onClick = { counter-- }) {
                    Text("-", fontSize = 20.sp)
                }
                Button(onClick = { counter++ }) {
                    Text("+", fontSize = 20.sp)
                }
            }

            HorizontalDivider()

            // 说明卡片
            ComposeFeatureCard()
        }
    }
}

@Composable
private fun ComposeFeatureCard() {
    Surface(
        color = MaterialTheme.colorScheme.surfaceVariant,
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "🎨 Compose Benefits",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            
            val features = listOf(
                "✅ Declarative UI - Auto-updates on state change",
                "✅ Kotlin-native - No XML needed",
                "✅ Composable - Build from small components",
                "✅ Live Preview - @Preview annotation",
                "✅ Less Code - Reduced boilerplate"
            )
            
            features.forEach { feature ->
                Text(
                    text = feature,
                    fontSize = 14.sp
                )
            }
        }
    }
}

