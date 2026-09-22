package com.ege.lockscreen.ui.screens

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.LockOpen
import androidx.compose.material.icons.filled.Security
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ege.lockscreen.model.ThemeConfig
import com.ege.lockscreen.ui.components.CharacterCanvas
import com.ege.lockscreen.ui.components.OrbOfAvariceCanvas
import com.ege.lockscreen.ui.components.PinIndicator
import com.ege.lockscreen.ui.components.RuneKeypad
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun LockScreenView(
    currentTheme: ThemeConfig,
    onNavigateToCodex: () -> Unit,
    modifier: Modifier = Modifier
) {
    var enteredPin by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }
    var isUnlocked by remember { mutableStateOf(false) }

    // Live Clock
    var currentTime by remember { mutableStateOf("") }
    var currentDate by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        val dateFormat = SimpleDateFormat("EEEE, MMMM d", Locale.getDefault())
        while (true) {
            val now = Date()
            currentTime = timeFormat.format(now)
            currentDate = dateFormat.format(now)
            delay(1000)
        }
    }

    // Passcode validation (demo accepts 123456 or 777777, or unlocks after 6 digits)
    fun handleDigit(d: Int) {
        if (enteredPin.length < 6 && !isUnlocked) {
            val next = enteredPin + d
            enteredPin = next
            if (next.length == 6) {
                if (next == "123456" || next == "777777" || next == "000000") {
                    isUnlocked = true
                } else {
                    // Flash error then clear
                    isError = true
                }
            }
        }
    }

    LaunchedEffect(isError) {
        if (isError) {
            delay(600)
            enteredPin = ""
            isError = false
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF030712),
                        Color(0xFF0A0F1D),
                        Color(0xFF030712)
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Header / Clock Section (Honor 10 Top Header)
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(top = 16.dp)
            ) {
                // Device & System Status Pill
                Row(
                    modifier = Modifier
                        .background(Color(0x3300E5FF), RoundedCornerShape(12.dp))
                        .border(1.dp, currentTheme.primaryColor.copy(alpha = 0.3f), RoundedCornerShape(12.dp))
                        .padding(horizontal = 10.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Security,
                        contentDescription = null,
                        tint = currentTheme.primaryColor,
                        modifier = Modifier.size(12.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "HONOR 10 • 19:9 EGE PROTOTYPE",
                        color = currentTheme.primaryColor,
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // High-Contrast Time
                Text(
                    text = if (currentTime.isEmpty()) "12:00" else currentTime,
                    color = Color.White,
                    fontSize = 52.sp,
                    fontWeight = FontWeight.Light,
                    fontFamily = FontFamily.SansSerif,
                    letterSpacing = 2.sp
                )

                Text(
                    text = if (currentDate.isEmpty()) "Loading Astral Cycle..." else currentDate.uppercase(),
                    color = currentTheme.secondaryColor.copy(alpha = 0.9f),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium,
                    letterSpacing = 1.5.sp
                )
            }

            // Visual Centerpiece: Character Canvas & Orb of Avarice
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                contentAlignment = Alignment.Center
            ) {
                CharacterCanvas(
                    primaryColor = currentTheme.primaryColor,
                    secondaryColor = currentTheme.secondaryColor
                )
                // Overlaid Orb of Avarice at the character core
                OrbOfAvariceCanvas(
                    modifier = Modifier.size(110.dp),
                    primaryColor = currentTheme.primaryColor,
                    secondaryColor = currentTheme.secondaryColor
                )
            }

            // Pin & Keypad Interaction Area
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(bottom = 12.dp)
            ) {
                // Status Prompt
                Text(
                    text = if (isUnlocked) "MONARCH SANCTUM UNLOCKED" else if (isError) "INCANTATION REJECTED" else "ENTER 6-DIGIT RUNIC PASSCODE (e.g. 1-2-3-4-5-6)",
                    color = if (isUnlocked) Color(0xFF10B981) else if (isError) Color(0xFFFF1744) else Color(0xFF9CA3AF),
                    fontSize = 10.sp,
                    fontWeight = FontWeight.SemiBold,
                    letterSpacing = 1.sp,
                    textAlign = TextAlign.Center
                )

                // 6-Digit Diamond Indicators
                PinIndicator(
                    pinLength = enteredPin.length,
                    maxDigits = 6,
                    primaryColor = currentTheme.primaryColor,
                    isError = isError
                )

                Spacer(modifier = Modifier.height(4.dp))

                // 0-9 Rune Keypad
                RuneKeypad(
                    onDigitClick = { handleDigit(it) },
                    onDeleteClick = {
                        if (enteredPin.isNotEmpty()) {
                            enteredPin = enteredPin.dropLast(1)
                        }
                    },
                    onClearClick = {
                        enteredPin = ""
                    },
                    primaryColor = currentTheme.primaryColor,
                    secondaryColor = currentTheme.secondaryColor
                )
            }
        }

        // Unlock Success Overlay
        AnimatedVisibility(
            visible = isUnlocked,
            enter = fadeIn() + scaleIn(),
            exit = fadeOut()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xE6030712))
                    .padding(24.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .background(Color(0xFF0F172A), RoundedCornerShape(24.dp))
                        .border(1.dp, currentTheme.primaryColor, RoundedCornerShape(24.dp))
                        .padding(24.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.LockOpen,
                        contentDescription = null,
                        tint = currentTheme.primaryColor,
                        modifier = Modifier.size(48.dp)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "MONARCH SANCTUM UNLOCKED",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "The Shadow Sovereign acknowledges your identity. All 10 primordial runes and the Infinite Codex are ready for consultation.",
                        color = Color(0xFF9CA3AF),
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = {
                            isUnlocked = false
                            enteredPin = ""
                            onNavigateToCodex()
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = currentTheme.primaryColor)
                    ) {
                        Text("Open Rune Codex", color = Color.Black, fontWeight = FontWeight.Bold)
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    TextButton(onClick = {
                        isUnlocked = false
                        enteredPin = ""
                    }) {
                        Text("Lock Screen Again", color = Color.White)
                    }
                }
            }
        }
    }
}
