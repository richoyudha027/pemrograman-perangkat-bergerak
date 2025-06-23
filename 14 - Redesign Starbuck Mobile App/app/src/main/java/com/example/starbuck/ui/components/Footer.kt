package com.example.starbuck.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.starbuck.ui.manager.CartManager

@Composable
fun Footer(
    modifier: Modifier = Modifier,
    onHomeClick: () -> Unit = {},
    onMenuClick: () -> Unit = {},
    onCartClick: () -> Unit = {},
    onAccountClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(Color.White)
            .border(
                width = 1.dp,
                color = Color.LightGray.copy(alpha = 0.5f),
                shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            FooterItem(
                icon = Icons.Default.Home,
                label = "Home",
                onClick = onHomeClick,
            )

            FooterItem(
                icon = Icons.Filled.MenuBook,
                label = "Menu",
                onClick = onMenuClick,
            )

            FooterItemWithBadge(
                icon = Icons.Default.ShoppingCart,
                label = "Cart",
                badgeCount = CartManager.getTotalItems(),
                onClick = onCartClick
            )

            FooterItem(
                icon = Icons.Default.AccountCircle,
                label = "Account",
                onClick = onAccountClick
            )
        }
    }
}

@Composable
fun FooterItem(
    icon: ImageVector,
    label: String,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(top = 7.dp, bottom = 7.dp)
            .fillMaxHeight()
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            modifier = Modifier.size(30.dp),
            tint = Color(0xFF046d3c)
        )
        Text(
            text = label,
            fontSize = 12.sp,
            color = Color(0xFF046d3c),
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun FooterItemWithBadge(
    icon: ImageVector,
    label: String,
    badgeCount: Int,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(top = 7.dp, bottom = 7.dp)
            .fillMaxHeight()
    ) {
        Box {
            Icon(
                imageVector = icon,
                contentDescription = label,
                modifier = Modifier.size(30.dp),
                tint = Color(0xFF046d3c)
            )
            if (badgeCount > 0) {
                Box(
                    modifier = Modifier
                        .size(16.dp)
                        .background(
                            Color.Red,
                            CircleShape
                        )
                        .align(Alignment.TopEnd)
                        .offset(x = 0.dp, y = (-2).dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = if (badgeCount > 99) "99+" else badgeCount.toString(),
                        color = Color.White,
                        fontSize = 8.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        lineHeight = 8.sp,
                        modifier = Modifier.offset(y = 1.dp)
                    )
                }
            }
        }

        Text(
            text = label,
            fontSize = 12.sp,
            color = Color(0xFF046d3c),
            fontWeight = FontWeight.Medium
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FooterPrev() {
    Footer(
        onHomeClick = {},
        onMenuClick = {},
        onCartClick = {},
        onAccountClick = {}
    )
}