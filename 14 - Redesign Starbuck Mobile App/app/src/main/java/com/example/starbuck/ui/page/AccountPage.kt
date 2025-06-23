package com.example.starbuck.ui.page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.starbuck.ui.components.Footer

@Composable
fun ProfileSection() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF046D3C))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .background(Color.White, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Profile",
                    modifier = Modifier.size(50.dp),
                    tint = Color(0xFF046D3C)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Richo",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Text(
                text = "richo@example.com",
                fontSize = 16.sp,
                color = Color.White.copy(alpha = 0.8f)
            )

            Spacer(modifier = Modifier.height(16.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .background(
                        Color.White.copy(alpha = 0.2f),
                        RoundedCornerShape(20.dp)
                    )
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Star",
                    tint = Color.Yellow,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Green Level",
                    color = Color.White,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Composable
fun MenuOption(
    icon: ImageVector,
    title: String,
    subtitle: String? = null,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
        onClick = onClick,
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF6F6F6)),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = Color(0xFF046D3C),
                modifier = Modifier.size(24.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black
                )
                if (subtitle != null) {
                    Text(
                        text = subtitle,
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }
            }

            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = "Arrow",
                tint = Color.Gray,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@Composable
fun AccountPage(
    modifier: Modifier = Modifier,
    onNavigateToHome: () -> Unit = {},
    onNavigateToMenu: () -> Unit = {},
    onNavigateToCart: () -> Unit = {},
    onNavigateToAccount: () -> Unit = {}
) {
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
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            Text(
                text = "Account",
                fontWeight = FontWeight.ExtraBold,
                fontSize = 32.sp,
                modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 8.dp)
            )
            ProfileSection()

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Account Settings",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )

            MenuOption(
                icon = Icons.Default.Person,
                title = "Edit Profile",
                subtitle = "Change your personal information"
            )

            MenuOption(
                icon = Icons.Default.Notifications,
                title = "Notifications",
                subtitle = "Manage notification preferences"
            )

            MenuOption(
                icon = Icons.Default.Security,
                title = "Security",
                subtitle = "Password and security settings"
            )

            MenuOption(
                icon = Icons.Default.Payment,
                title = "Payment Methods",
                subtitle = "Manage your payment options"
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Rewards & Benefits",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )

            MenuOption(
                icon = Icons.Default.Star,
                title = "My Rewards",
                subtitle = "View your stars and rewards"
            )

            MenuOption(
                icon = Icons.Default.History,
                title = "Order History",
                subtitle = "View your past orders"
            )

            MenuOption(
                icon = Icons.Default.CardGiftcard,
                title = "Gift Cards",
                subtitle = "Manage your gift cards"
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Support",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )

            MenuOption(
                icon = Icons.Default.Help,
                title = "Help Center",
                subtitle = "Get help and support"
            )

            MenuOption(
                icon = Icons.Default.Info,
                title = "About",
                subtitle = "App version and information"
            )

            MenuOption(
                icon = Icons.Default.Feedback,
                title = "Send Feedback",
                subtitle = "Help us improve the app"
            )

            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = { /* TODO: Implement logout */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red.copy(alpha = 0.1f),
                    contentColor = Color.Red
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Logout,
                    contentDescription = "Logout",
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Logout",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AccountPagePreview() {
    AccountPage()
}