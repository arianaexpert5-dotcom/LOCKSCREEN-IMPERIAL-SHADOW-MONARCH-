package com.ege.lockscreen.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PinIndicator(
    pinLength: Int,
    maxDigits: Int = 6,
    primaryColor: Color = Color(0xFF00E5FF),
    isError: Boolean = false,
    modifier: Modifier = Modifier
) {
    val shakeOffset by animateFloatAsState(
        targetValue = if (isError) 1f else 0f,
        animationSpec = spring(dampingRatio = Spring.DampingRatioHighBouncy, stiffness = Spring.StiffnessMedium),
        label = "Shake"
    )

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
            .offset(x = (shakeOffset * if (isError) 10 else 0).dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (i in 0 until maxDigits) {
            val isFilled = i < pinLength
            val dotColor by animateColorAsState(
                targetValue = when {
                    isError -> Color(0xFFFF1744)
                    isFilled -> primaryColor
                    else -> Color(0xFF1E293B)
                },
                animationSpec = tween(200),
                label = "DotColor"
            )

            Box(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .size(16.dp)
                    .rotate(45f) // Diamond shape
                    .background(dotColor, RoundedCornerShape(2.dp))
                    .border(
                        width = 1.dp,
                        color = if (isFilled) primaryColor else Color(0x6600E5FF),
                        shape = RoundedCornerShape(2.dp)
                    )
            )
        }
    }
}
