package com.ege.lockscreen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ege.lockscreen.model.THEME_PRESETS
import com.ege.lockscreen.model.ThemeConfig
import com.ege.lockscreen.theme.EGELockScreenTheme
import com.ege.lockscreen.ui.screens.CodexScreen
import com.ege.lockscreen.ui.screens.LockScreenView
import com.ege.lockscreen.ui.screens.SettingsScreen
import com.ege.lockscreen.ui.screens.ThemeCustomizerScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EGELockScreenTheme {
                MainApp()
            }
        }
    }
}

@Composable
fun MainApp() {
    var currentTheme by remember { mutableStateOf<ThemeConfig>(THEME_PRESETS[0]) }
    var currentTab by remember { mutableIntStateOf(0) }

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = Color(0xFF030712),
                contentColor = currentTheme.primaryColor,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                    .border(1.dp, Color(0xFF1E293B), RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)),
                tonalElevation = 8.dp
            ) {
                NavigationBarItem(
                    selected = currentTab == 0,
                    onClick = { currentTab = 0 },
                    icon = { Icon(Icons.Default.Lock, contentDescription = "Lock") },
                    label = { Text("Lock", fontSize = 10.sp) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = currentTheme.primaryColor,
                        selectedTextColor = currentTheme.primaryColor,
                        unselectedIconColor = Color(0xFF6B7280),
                        unselectedTextColor = Color(0xFF6B7280),
                        indicatorColor = Color(0x3300E5FF)
                    )
                )

                NavigationBarItem(
                    selected = currentTab == 1,
                    onClick = { currentTab = 1 },
                    icon = { Icon(Icons.Default.Book, contentDescription = "Codex") },
                    label = { Text("Codex", fontSize = 10.sp) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = currentTheme.primaryColor,
                        selectedTextColor = currentTheme.primaryColor,
                        unselectedIconColor = Color(0xFF6B7280),
                        unselectedTextColor = Color(0xFF6B7280),
                        indicatorColor = Color(0x3300E5FF)
                    )
                )

                NavigationBarItem(
                    selected = currentTab == 2,
                    onClick = { currentTab = 2 },
                    icon = { Icon(Icons.Default.Palette, contentDescription = "Themes") },
                    label = { Text("Themes", fontSize = 10.sp) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = currentTheme.primaryColor,
                        selectedTextColor = currentTheme.primaryColor,
                        unselectedIconColor = Color(0xFF6B7280),
                        unselectedTextColor = Color(0xFF6B7280),
                        indicatorColor = Color(0x3300E5FF)
                    )
                )

                NavigationBarItem(
                    selected = currentTab == 3,
                    onClick = { currentTab = 3 },
                    icon = { Icon(Icons.Default.Settings, contentDescription = "Settings") },
                    label = { Text("Settings", fontSize = 10.sp) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = currentTheme.primaryColor,
                        selectedTextColor = currentTheme.primaryColor,
                        unselectedIconColor = Color(0xFF6B7280),
                        unselectedTextColor = Color(0xFF6B7280),
                        indicatorColor = Color(0x3300E5FF)
                    )
                )
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFF030712))
        ) {
            when (currentTab) {
                0 -> LockScreenView(
                    currentTheme = currentTheme,
                    onNavigateToCodex = { currentTab = 1 }
                )
                1 -> CodexScreen(currentTheme = currentTheme)
                2 -> ThemeCustomizerScreen(
                    currentTheme = currentTheme,
                    onSelectTheme = { currentTheme = it }
                )
                3 -> SettingsScreen(currentTheme = currentTheme)
            }
        }
    }
}
