package com.example.starbuck.ui.page

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CardGiftcard
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.starbuck.ui.components.Footer
import com.example.starbuck.R
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold

@Composable
fun NavigationSlider(
    totalDots: Int = 5,
    currentIndex: Int = 0,
    modifier: Modifier = Modifier,
    activeColor: Color = Color(0xFF046d3c),
    inactiveColor: Color = Color(0xFFE0E0E0)
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(totalDots) { index ->
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .background(
                        color = if (index == currentIndex) activeColor else inactiveColor,
                        shape = CircleShape
                    )
            )

            if (index < totalDots - 1) {
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }
}

@Composable
fun HomePage(
    modifier: Modifier = Modifier,
    onNavigateToHome: () -> Unit = {},
    onNavigateToMenu: () -> Unit = {},
    onNavigateToCart: () -> Unit = {},
    onNavigateToAccount: () -> Unit = {}
){
    val scrollState = rememberScrollState()
    Scaffold(
        bottomBar = {
            Footer(
                onHomeClick = onNavigateToHome,
                onMenuClick = onNavigateToMenu,
                onCartClick = onNavigateToCart,
                onAccountClick = onNavigateToAccount
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                .verticalScroll(scrollState)
        ){
            Box(
                modifier = Modifier
                    .padding(top = 16.dp, bottom = 16.dp)
                    .fillMaxWidth()
                    .height(150.dp)
                    .clip(RoundedCornerShape(28.dp))
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                Color(0xFF046d3c),
                                Color(0xFF519a7b)
                            ),
                            start = Offset(0f, 0f),
                            end = Offset(0f, Float.POSITIVE_INFINITY)
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        Column(
                            modifier = Modifier.weight(1f)
                        ){
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = "Good Morning,",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.White
                            )
                            Spacer(Modifier.height(4.dp))
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = "Richo",
                                fontSize = 28.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }
                        Icon(
                            painter = painterResource(R.drawable.starbuck),
                            contentDescription = "Starbuck",
                            tint = Color.Unspecified,
                            modifier = Modifier.size(64.dp)
                        )
                    }
                    Spacer(Modifier.height(12.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(24.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically)
                        {
                            Icon(
                                imageVector = Icons.Filled.Notifications,
                                contentDescription = "Notifications",
                                tint = Color.White,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "Notifications",
                                fontSize = 16.sp,
                                color = Color.White
                            )
                        }

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Filled.CardGiftcard,
                                contentDescription = "Voucher",
                                tint = Color.White,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "E-Code",
                                fontSize = 16.sp,
                                color = Color.White
                            )
                        }

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Filled.History,
                                contentDescription = "History",
                                tint = Color.White,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "History",
                                fontSize = 16.sp,
                                color = Color.White
                            )
                        }
                    }
                }
            }

            Spacer(Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(
                    text = "Green Level",
                    fontSize = 24.sp,
                    color = Color(0xFF046d3c),
                    fontWeight = FontWeight.SemiBold
                )

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "0",
                        fontSize = 24.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = "Star",
                        tint = Color(0xFF046d3c),
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            Icon(
                painter = painterResource(R.drawable.star),
                contentDescription = "Star Progress",
                tint = Color.Unspecified,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(86.dp)
            )

            Spacer(Modifier.height(4.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF046d3c),
                        contentColor = Color.White
                    )
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Text(text = "Redeem")
                        Spacer(modifier = Modifier.width(2.dp))
                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = "Star",
                            tint = Color.Yellow,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }

                OutlinedButton(
                    onClick = {},
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Color(0xFF046d3c)
                    ),
                    border = BorderStroke(1.dp, Color(0xFF046d3c))
                ) {
                    Text(text = "Details")
                }
            }
            Spacer(Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Programs and Promotion",
                    fontSize = 20.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "See All",
                    fontSize = 16.sp,
                    color = Color(0xFF046d3c),
                    fontWeight = FontWeight.Medium
                )
            }
            Spacer(Modifier.height(8.dp))
            Icon(
                painter = painterResource(R.drawable.content_promo),
                contentDescription = "Star Progress",
                tint = Color.Unspecified,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .clip(RoundedCornerShape(36.dp))
            )
            Spacer(Modifier.height(4.dp))
            NavigationSlider(
                totalDots = 5,
                currentIndex = 0,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Starbucks News",
                    fontSize = 20.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "See All",
                    fontSize = 16.sp,
                    color = Color(0xFF046d3c),
                    fontWeight = FontWeight.Medium
                )
            }
            Spacer(Modifier.height(8.dp))
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.Top,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        painter = painterResource(R.drawable.starbuck_news),
                        contentDescription = "News Image",
                        tint = Color.Unspecified,
                        modifier = Modifier
                            .size(86.dp)
                            .clip(RoundedCornerShape(12.dp))
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = "Starbucks Opens in Your City!",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            maxLines = 2
                        )
                        Spacer(Modifier.height(6.dp))
                        Text(
                            text = "We're excited to open our newest store in downtown Surabaya! Be one of the first 100 customers and enjoy a free tall-sized beverage.",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.Gray,
                            textAlign = TextAlign.Justify,
                            lineHeight = 18.sp,
                            maxLines = 3
                        )
                    }
                }

                Row(
                    verticalAlignment = Alignment.Top,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        painter = painterResource(R.drawable.starbuck_news),
                        contentDescription = "News Image",
                        tint = Color.Unspecified,
                        modifier = Modifier
                            .size(86.dp)
                            .clip(RoundedCornerShape(12.dp))
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = "New Seasonal Menu Available!",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            maxLines = 2
                        )
                        Spacer(Modifier.height(6.dp))
                        Text(
                            text = "Try our new holiday-inspired beverages and food items. Limited time offer with special discounts for members.",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.Gray,
                            textAlign = TextAlign.Justify,
                            lineHeight = 18.sp,
                            maxLines = 3
                        )
                    }
                }

                Row(
                    verticalAlignment = Alignment.Top,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        painter = painterResource(R.drawable.starbuck_news),
                        contentDescription = "News Image",
                        tint = Color.Unspecified,
                        modifier = Modifier
                            .size(86.dp)
                            .clip(RoundedCornerShape(12.dp))
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = "Earn Double Stars This Week!",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            maxLines = 2
                        )
                        Spacer(Modifier.height(6.dp))
                        Text(
                            text = "Get double stars on all purchases this week. Perfect opportunity to level up your rewards and unlock exclusive benefits.",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.Gray,
                            textAlign = TextAlign.Justify,
                            lineHeight = 18.sp,
                            maxLines = 3
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePagePrev() {
    HomePage()
}