package com.ege.lockscreen.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Smartphone
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ege.lockscreen.model.ThemeConfig

@Composable
fun SettingsScreen(
    currentTheme: ThemeConfig,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF030712))
            .statusBarsPadding()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(bottom = 80.dp)
    ) {
        item {
            Column(modifier = Modifier.padding(top = 12.dp)) {
                Text(
                    text = "SETTINGS & ARCHITECTURE",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )
                Text(
                    text = "Honor 10 native target parameters and security disclosures",
                    color = Color(0xFF9CA3AF),
                    fontSize = 11.sp
                )
            }
        }

        // Security Architecture Notice (MANDATORY REQUIREMENT)
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF0D1424), RoundedCornerShape(14.dp))
                    .border(1.dp, Color(0x6600E5FF), RoundedCornerShape(14.dp))
                    .padding(16.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Security,
                        contentDescription = null,
                        tint = currentTheme.primaryColor,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "SECURITY ARCHITECTURE",
                        color = currentTheme.primaryColor,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "• The application DOES NOT intercept, store, read, or replace the user's real Android device PIN or password.\n\n" +
                            "• The native Android authentication system (Biometrics, Lockscreen Gatekeeper) remains strictly responsible for actual device security.\n\n" +
                            "• This application functions as an official EGE Lock Screen personalization interface prototype, adhering strictly to Android sandboxing and EMUI security policies.",
                    color = Color(0xFFE2E8F0),
                    fontSize = 11.sp,
                    lineHeight = 16.sp
                )
            }
        }

        // Device Target: Honor 10
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF0D1424), RoundedCornerShape(14.dp))
                    .border(1.dp, Color(0xFF1E293B), RoundedCornerShape(14.dp))
                    .padding(16.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Smartphone,
                        contentDescription = null,
                        tint = currentTheme.secondaryColor,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "DEVICE OPTIMIZATION: HONOR 10",
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                val specs = listOf(
                    "Display Resolution" to "1080 × 2280 pixels",
                    "Aspect Ratio" to "19:9 (Tall FullView Display)",
                    "Pixel Density" to "~432 PPI",
                    "Target Architecture" to "Android 8.1 (Oreo) / EMUI 8.1+ / Android 14",
                    "UI Framework" to "Jetpack Compose + Material 3",
                    "Application ID" to "com.ege.lockscreen"
                )

                specs.forEach { (label, value) ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = label, color = Color(0xFF9CA3AF), fontSize = 11.sp)
                        Text(text = value, color = Color.White, fontSize = 11.sp, fontWeight = FontWeight.Medium)
                    }
                }
            }
        }

        // Build & Package Information
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF0D1424), RoundedCornerShape(14.dp))
                    .border(1.dp, Color(0xFF1E293B), RoundedCornerShape(14.dp))
                    .padding(16.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = null,
                        tint = Color(0xFF10B981),
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "APK & BUILD INFORMATION",
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Project: EGE — LOCK SCREEN\n" +
                            "Output APK: app/build/outputs/apk/debug/app-debug.apk\n" +
                            "Target Platform: Android (minSdk 26, targetSdk 34)\n" +
                            "Build Engine: Gradle 8.5 with Android Gradle Plugin 8.2.2",
                    color = Color(0xFFCBD5E1),
                    fontSize = 11.sp,
                    lineHeight = 16.sp
                )
            }
        }
    }
}
