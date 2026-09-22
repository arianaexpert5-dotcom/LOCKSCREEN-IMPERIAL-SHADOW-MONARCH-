package com.ege.lockscreen.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

@Composable
fun CharacterCanvas(
    modifier: Modifier = Modifier,
    primaryColor: Color = Color(0xFF00E5FF),
    secondaryColor: Color = Color(0xFFA855F7),
    auraIntensity: Float = 1.0f
) {
    val transition = rememberInfiniteTransition(label = "EyeGlow")
    val eyeGlow by transition.animateFloat(
        initialValue = 0.6f,
        targetValue = 1.0f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "Glow"
    )

    Canvas(modifier = modifier.fillMaxWidth().height(220.dp)) {
        val w = size.width
        val h = size.height
        val cx = w / 2f

        // 1. Shadow Flame Wings Aura
        val wingLeft = Path().apply {
            moveTo(cx, h * 0.9f)
            cubicTo(cx - w * 0.3f, h * 0.7f, cx - w * 0.45f, h * 0.3f, cx - w * 0.25f, h * 0.1f)
            cubicTo(cx - w * 0.15f, h * 0.4f, cx - w * 0.05f, h * 0.6f, cx, h * 0.9f)
            close()
        }
        val wingRight = Path().apply {
            moveTo(cx, h * 0.9f)
            cubicTo(cx + w * 0.3f, h * 0.7f, cx + w * 0.45f, h * 0.3f, cx + w * 0.25f, h * 0.1f)
            cubicTo(cx + w * 0.15f, h * 0.4f, cx + w * 0.05f, h * 0.6f, cx, h * 0.9f)
            close()
        }

        drawPath(
            path = wingLeft,
            brush = Brush.verticalGradient(
                colors = listOf(primaryColor.copy(alpha = 0.25f * auraIntensity), Color.Transparent)
            )
        )
        drawPath(
            path = wingRight,
            brush = Brush.verticalGradient(
                colors = listOf(secondaryColor.copy(alpha = 0.25f * auraIntensity), Color.Transparent)
            )
        )

        // 2. Armor & Coat Silhouette
        val coatPath = Path().apply {
            moveTo(cx - 50.dp.toPx(), h)
            lineTo(cx - 35.dp.toPx(), h * 0.55f)
            lineTo(cx - 20.dp.toPx(), h * 0.4f)
            lineTo(cx, h * 0.45f)
            lineTo(cx + 20.dp.toPx(), h * 0.4f)
            lineTo(cx + 35.dp.toPx(), h * 0.55f)
            lineTo(cx + 50.dp.toPx(), h)
            close()
        }
        drawPath(
            path = coatPath,
            brush = Brush.verticalGradient(
                colors = listOf(Color(0xFF0F172A), Color(0xFF030712))
            )
        )
        drawPath(
            path = coatPath,
            color = primaryColor.copy(alpha = 0.4f),
            style = Stroke(width = 1.5.dp.toPx())
        )

        // 3. Head & Hair Contours
        val headPath = Path().apply {
            moveTo(cx, h * 0.22f)
            lineTo(cx - 18.dp.toPx(), h * 0.36f)
            lineTo(cx, h * 0.42f)
            lineTo(cx + 18.dp.toPx(), h * 0.36f)
            close()
        }
        drawPath(
            path = headPath,
            color = Color(0xFF030712)
        )

        // 4. Monarch Glowing Sovereign Eyes (Sung Jin-Woo signature blue glow)
        val eyeY = h * 0.34f
        val leftEye = Offset(cx - 8.dp.toPx(), eyeY)
        val rightEye = Offset(cx + 8.dp.toPx(), eyeY)

        // Eye glow halos
        drawCircle(
            color = primaryColor.copy(alpha = 0.8f * eyeGlow),
            radius = 5.dp.toPx(),
            center = leftEye
        )
        drawCircle(
            color = primaryColor.copy(alpha = 0.8f * eyeGlow),
            radius = 5.dp.toPx(),
            center = rightEye
        )
        // Bright centers
        drawCircle(color = Color.White, radius = 2.dp.toPx(), center = leftEye)
        drawCircle(color = Color.White, radius = 2.dp.toPx(), center = rightEye)

        // Eye streak emission
        drawLine(
            color = primaryColor.copy(alpha = 0.7f * eyeGlow),
            start = leftEye,
            end = Offset(leftEye.x - 14.dp.toPx(), leftEye.y - 4.dp.toPx()),
            strokeWidth = 2.dp.toPx()
        )
        drawLine(
            color = primaryColor.copy(alpha = 0.7f * eyeGlow),
            start = rightEye,
            end = Offset(rightEye.x + 14.dp.toPx(), rightEye.y - 4.dp.toPx()),
            strokeWidth = 2.dp.toPx()
        )
    }
}
