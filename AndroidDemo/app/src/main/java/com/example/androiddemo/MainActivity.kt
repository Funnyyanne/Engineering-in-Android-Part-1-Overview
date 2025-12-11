package com.example.androiddemo

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddemo.compose.ComposeExampleScreen
import com.example.androiddemo.compose.McLarenComposeGrid
import com.example.androiddemo.ui.theme.AndroidDemoTheme
import com.example.androiddemo.xml.McLarenXmlActivity

/**
 * MainActivity - Application Entry Point*
 * Built with Jetpack Compose
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
        setContent {
            AndroidDemoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

/**
 * Main navigation screen with tabs
 *
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val context = LocalContext.current
    var currentScreen by remember { mutableStateOf<Screen>(Screen.Home) }
    
    when (currentScreen) {
        Screen.Home -> {
            HomeScreen(
                onNavigate = { screen -> currentScreen = screen },
                onStartXmlActivity = { activityClass ->
                    context.startActivity(Intent(context, activityClass))
                }
            )
        }
        Screen.McLarenCompose -> {
            McLarenComposeScreen(
                onBack = { currentScreen = Screen.Home }
            )
        }
        Screen.ComposeExample -> {
            ComposeExampleScreen()
        }
    }
}

/**
 * Screen navigation enum
 */
sealed class Screen {
    data object Home : Screen()
    data object McLarenCompose : Screen()
    data object ComposeExample : Screen()
}

/**
 * Home screen with navigation options
 * 带导航选项的主页
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onNavigate: (Screen) -> Unit,
    onStartXmlActivity: (Class<*>) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Column {
                        Text(
                            "Android Part 1 Demo",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                        Text(
                            "Kotlin + Compose + Gradle",
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            
            // McLaren 展示入口 (重点演示)
            McLarenShowcaseCard(
                onComposeClick = { onNavigate(Screen.McLarenCompose) },
                onXmlClick = { onStartXmlActivity(McLarenXmlActivity::class.java) })

            // 构建信息卡片
            BuildInfoCard()
        }
    }
}

/**
 * McLaren Compose Screen with back navigation
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun McLarenComposeScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Column {
                        Text(
                            "McLaren Gallery",
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            "Compose • LazyVerticalStaggeredGrid • Coil",
                            fontSize = 12.sp,
                            color = Color(0xFFFF6B00)
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF1A1A1A),
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFF121212))
        ) {
            McLarenComposeGrid()
        }
    }
}

/**
 * McLaren Showcase Card - Main demo entry point
 */
@Composable
fun McLarenShowcaseCard(
    onComposeClick: () -> Unit,
    onXmlClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF1E1E1E)
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "🏎️",
                    fontSize = 28.sp
                )
                Column {
                    Text(
                        text = "McLaren Showcase",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = "Staggered Grid • Coil Image Loading",
                        fontSize = 13.sp,
                        color = Color(0xFFFF6B00)
                    )
                }
            }
            
            Text(
                text = "Compare XML vs Compose implementations of a waterfall/masonry layout with network image loading using Coil.",
                fontSize = 14.sp,
                color = Color(0xFFB0B0B0),
                lineHeight = 20.sp
            )
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Compose Button
                Button(
                    onClick = onComposeClick,
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFF6B00)
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(vertical = 4.dp)
                    ) {
                        Text(
                            text = "Compose",
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "StaggeredGrid",
                            fontSize = 10.sp,
                            color = Color.White.copy(alpha = 0.8f)
                        )
                    }
                }
                
                // XML Button
                OutlinedButton(
                    onClick = onXmlClick,
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Color(0xFFFF6B00)
                    )
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(vertical = 4.dp)
                    ) {
                        Text(
                            text = "XML",
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "RecyclerView",
                            fontSize = 10.sp,
                            color = Color(0xFFFF6B00).copy(alpha = 0.8f)
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun OtherExamplesCard(
    onXmlClick: () -> Unit,
    onComposeClick: () -> Unit,
    onKotlinClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "🔍 Other Examples",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = onXmlClick,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("XML Demo")
                }
                
                Button(
                    onClick = onComposeClick,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Compose")
                }
                
                Button(
                    onClick = onKotlinClick,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Kotlin")
                }
            }
        }
    }
}

@Composable
fun BuildInfoCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "🔧 Build Info",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
            
            HorizontalDivider()
            
            // Display BuildConfig info
            BuildInfoRow("Channel", BuildConfig.CHANNEL_NAME)
            BuildInfoRow("Channel Code", BuildConfig.CHANNEL_CODE.toString())
            BuildInfoRow("API URL", BuildConfig.BASE_URL)
            BuildInfoRow("Debug Mode", BuildConfig.DEBUG.toString())
            BuildInfoRow("Version", "${BuildConfig.VERSION_NAME} (${BuildConfig.VERSION_CODE})")
            BuildInfoRow("App ID", BuildConfig.APPLICATION_ID)
        }
    }
}

@Composable
fun BuildInfoRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onSecondaryContainer
        )
        Text(
            text = value,
            color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.7f)
        )
    }
}
