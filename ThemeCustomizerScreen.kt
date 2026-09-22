package com.ege.lockscreen.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ege.lockscreen.model.THEME_PRESETS
import com.ege.lockscreen.model.ThemeConfig
import com.ege.lockscreen.ui.components.CharacterCanvas
import com.ege.lockscreen.ui.components.OrbOfAvariceCanvas

@Composable
fun ThemeCustomizerScreen(
    currentTheme: ThemeConfig,
    onSelectTheme: (ThemeConfig) -> Unit,
    modifier: Modifier = Modifier
) {
    var auraIntensity by remember { mutableFloatStateOf(1.0f) }
    var particleDensity by remember { mutableFloatStateOf(1.0f) }

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
                    text = "THEMES & AURA ENGINE",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )
                Text(
                    text = "Customize the visual appearance, aura flow, and character archetype",
                    color = Color(0xFF9CA3AF),
                    fontSize = 11.sp
                )
            }
        }

        // Live Preview Box
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(Color(0xFF0D1424), RoundedCornerShape(16.dp))
                    .border(1.dp, currentTheme.primaryColor.copy(alpha = 0.4f), RoundedCornerShape(16.dp)),
                contentAlignment = Alignment.Center
            ) {
                CharacterCanvas(
                    primaryColor = currentTheme.primaryColor,
                    secondaryColor = currentTheme.secondaryColor,
                    auraIntensity = auraIntensity
                )
                OrbOfAvariceCanvas(
                    modifier = Modifier.size(100.dp),
                    primaryColor = currentTheme.primaryColor,
                    secondaryColor = currentTheme.secondaryColor,
                    intensity = auraIntensity
                )
            }
        }

        // Preset Themes
        item {
            Text(
                text = "Preset Monarch Themes",
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                THEME_PRESETS.forEach { theme ->
                    val isSelected = theme.id == currentTheme.id
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(if (isSelected) Color(0xFF1E293B) else Color(0xFF0D1424))
                            .border(
                                width = if (isSelected) 2.dp else 1.dp,
                                color = if (isSelected) theme.primaryColor else Color(0xFF1E293B),
                                shape = RoundedCornerShape(12.dp)
                            )
                            .clickable { onSelectTheme(theme) }
                            .padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(24.dp)
                                    .clip(CircleShape)
                                    .background(theme.primaryColor)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Box(
                                modifier = Modifier
                                    .size(24.dp)
                                    .clip(CircleShape)
                                    .background(theme.secondaryColor)
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Column {
                                Text(
                                    text = theme.name,
                                    color = Color.White,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = "${theme.characterName} • ${theme.characterSubtitle}",
                                    color = Color(0xFF9CA3AF),
                                    fontSize = 10.sp
                                )
                            }
                        }

                        if (isSelected) {
                            Text(
                                text = "ACTIVE",
                                color = theme.primaryColor,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }

        // Aura Sliders
        item {
            Text(
                text = "Aura & Orb Calibration",
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF0D1424), RoundedCornerShape(12.dp))
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Aura Resonance Intensity", color = Color.White, fontSize = 12.sp)
                    Text("${(auraIntensity * 100).toInt()}%", color = currentTheme.primaryColor, fontSize = 12.sp)
                }
                Slider(
                    value = auraIntensity,
                    onValueChange = { auraIntensity = it },
                    valueRange = 0.5f..1.5f,
                    colors = SliderDefaults.colors(
                        thumbColor = currentTheme.primaryColor,
                        activeTrackColor = currentTheme.primaryColor
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Particle Energy Flux", color = Color.White, fontSize = 12.sp)
                    Text("${(particleDensity * 100).toInt()}%", color = currentTheme.secondaryColor, fontSize = 12.sp)
                }
                Slider(
                    value = particleDensity,
                    onValueChange = { particleDensity = it },
                    valueRange = 0.5f..2.0f,
                    colors = SliderDefaults.colors(
                        thumbColor = currentTheme.secondaryColor,
                        activeTrackColor = currentTheme.secondaryColor
                    )
                )
            }
        }
    }
}
