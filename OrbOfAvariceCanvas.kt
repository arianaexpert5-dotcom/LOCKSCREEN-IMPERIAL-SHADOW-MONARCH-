package com.ege.lockscreen.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun OrbOfAvariceCanvas(
    modifier: Modifier = Modifier,
    primaryColor: Color = Color(0xFF00E5FF),
    secondaryColor: Color = Color(0xFFA855F7),
    intensity: Float = 1.0f
) {
    val infiniteTransition = rememberInfiniteTransition(label = "OrbTransition")

    val pulse by infiniteTransition.animateFloat(
        initialValue = 0.85f,
        targetValue = 1.15f,
        animationSpec = infiniteRepeatable(
            animation = tween(2200, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "OrbPulse"
    )

    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(12000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "OrbRotate"
    )

    Canvas(modifier = modifier.size(140.dp)) {
        val center = Offset(size.width / 2f, size.height / 2f)
        val baseRadius = (size.minDimension / 3.2f) * pulse * intensity

        // 1. Ambient Outer Halo
        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(
                    secondaryColor.copy(alpha = 0.45f),
                    primaryColor.copy(alpha = 0.15f),
                    Color.Transparent
                ),
                center = center,
                radius = baseRadius * 1.8f
            ),
            radius = baseRadius * 1.8f,
            center = center
        )

        // 2. Rotating Arcane Energy Rings
        val rad = Math.toRadians(rotation.toDouble())
        val ringCount = 3
        for (i in 0 until ringCount) {
            val ringAngle = rad + (i * Math.PI / ringCount)
            val rx = baseRadius * 1.25f
            val ry = baseRadius * 0.45f
            
            drawCircle(
                color = primaryColor.copy(alpha = 0.5f),
                radius = rx,
                center = center,
                style = Stroke(width = 2.dp.toPx())
            )
        }

        // 3. Dense Glowing Core
        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(
                    Color.White,
                    primaryColor,
                    secondaryColor,
                    Color(0xFF091428)
                ),
                center = center,
                radius = baseRadius
            ),
            radius = baseRadius,
            center = center
        )

        // 4. Orbiting Core Sparks
        for (k in 0..7) {
            val sparkAngle = rad * 1.5 + (k * (Math.PI * 2 / 8))
            val dist = baseRadius * (1.1f + 0.2f * sin(sparkAngle + k).toFloat())
            val sparkPos = Offset(
                x = center.x + (cos(sparkAngle) * dist).toFloat(),
                y = center.y + (sin(sparkAngle) * dist).toFloat()
            )
            drawCircle(
                color = Color.White.copy(alpha = 0.85f),
                radius = 3.dp.toPx(),
                center = sparkPos
            )
        }
    }
}
