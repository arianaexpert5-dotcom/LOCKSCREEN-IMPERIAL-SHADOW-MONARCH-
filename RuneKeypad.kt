package com.ege.lockscreen.ui.components

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Backspace
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ege.lockscreen.model.DEFAULT_RUNES

@Composable
fun RuneKeypad(
    onDigitClick: (Int) -> Unit,
    onDeleteClick: () -> Unit,
    onClearClick: () -> Unit,
    primaryColor: Color = Color(0xFF00E5FF),
    secondaryColor: Color = Color(0xFFA855F7),
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val vibrator = remember { context.getSystemService(Context.VIBRATOR_SERVICE) as? Vibrator }

    fun triggerHaptic() {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator?.vibrate(VibrationEffect.createOneShot(25, VibrationEffect.DEFAULT_AMPLITUDE))
            } else {
                @Suppress("DEPRECATION")
                vibrator?.vibrate(25)
            }
        } catch (_: Exception) {}
    }

    val runeMap = remember { DEFAULT_RUNES.associateBy { it.digit } }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterVertically,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        // Row 1: 1, 2, 3
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            RuneKeyButton(1, runeMap[1]?.glyph ?: "ᛁ", "IL", primaryColor, secondaryColor) {
                triggerHaptic()
                onDigitClick(1)
            }
            RuneKeyButton(2, runeMap[2]?.glyph ?: "ᛝ", "YI", primaryColor, secondaryColor) {
                triggerHaptic()
                onDigitClick(2)
            }
            RuneKeyButton(3, runeMap[3]?.glyph ?: "ᛟ", "SAM", primaryColor, secondaryColor) {
                triggerHaptic()
                onDigitClick(3)
            }
        }

        // Row 2: 4, 5, 6
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            RuneKeyButton(4, runeMap[4]?.glyph ?: "ᚦ", "SA", primaryColor, secondaryColor) {
                triggerHaptic()
                onDigitClick(4)
            }
            RuneKeyButton(5, runeMap[5]?.glyph ?: "ᚨ", "OH", primaryColor, secondaryColor) {
                triggerHaptic()
                onDigitClick(5)
            }
            RuneKeyButton(6, runeMap[6]?.glyph ?: "ᚱ", "YUK", primaryColor, secondaryColor) {
                triggerHaptic()
                onDigitClick(6)
            }
        }

        // Row 3: 7, 8, 9
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            RuneKeyButton(7, runeMap[7]?.glyph ?: "ᛊ", "CHIL", primaryColor, secondaryColor) {
                triggerHaptic()
                onDigitClick(7)
            }
            RuneKeyButton(8, runeMap[8]?.glyph ?: "ᛏ", "PAL", primaryColor, secondaryColor) {
                triggerHaptic()
                onDigitClick(8)
            }
            RuneKeyButton(9, runeMap[9]?.glyph ?: "ᚹ", "GU", primaryColor, secondaryColor) {
                triggerHaptic()
                onDigitClick(9)
            }
        }

        // Row 4: Clear, 0 (Void), Backspace
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Action Button: Clear
            Box(
                modifier = Modifier
                    .size(68.dp)
                    .clip(CircleShape)
                    .clickable {
                        triggerHaptic()
                        onClearClick()
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "CLR",
                    color = Color.Gray,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            // Digit 0: MU / VOID
            RuneKeyButton(0, runeMap[0]?.glyph ?: "ᛈ", "MU", primaryColor, secondaryColor) {
                triggerHaptic()
                onDigitClick(0)
            }

            // Action Button: Backspace
            Box(
                modifier = Modifier
                    .size(68.dp)
                    .clip(CircleShape)
                    .clickable {
                        triggerHaptic()
                        onDeleteClick()
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.Backspace,
                    contentDescription = "Backspace",
                    tint = primaryColor.copy(alpha = 0.8f),
                    modifier = Modifier.size(22.dp)
                )
            }
        }
    }
}

@Composable
fun RuneKeyButton(
    digit: Int,
    glyph: String,
    title: String,
    primaryColor: Color,
    secondaryColor: Color,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val bgBrush = if (isPressed) {
        Brush.radialGradient(
            colors = listOf(primaryColor.copy(alpha = 0.4f), secondaryColor.copy(alpha = 0.2f), Color(0xFF0F172A))
        )
    } else {
        Brush.verticalGradient(
            colors = listOf(Color(0xFF0F172A), Color(0xFF030712))
        )
    }

    val borderColor = if (isPressed) primaryColor else primaryColor.copy(alpha = 0.35f)

    Box(
        modifier = Modifier
            .size(68.dp)
            .clip(CircleShape)
            .background(bgBrush)
            .border(1.dp, borderColor, CircleShape)
            .clickable(interactionSource = interactionSource, indication = null) {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = digit.toString(),
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = glyph,
                    color = primaryColor,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Light
                )
            }
            Text(
                text = title,
                color = secondaryColor.copy(alpha = 0.9f),
                fontSize = 8.sp,
                fontWeight = FontWeight.SemiBold,
                letterSpacing = 1.sp
            )
        }
    }
}
