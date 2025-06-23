package com.example.starbuck.ui.page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.starbuck.ui.components.Footer
import com.example.starbuck.ui.manager.CartItem
import com.example.starbuck.ui.manager.CartManager

@Composable
fun CartItemRow(
    cartItem: CartItem,
    onAddItem: () -> Unit,
    onRemoveItem: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
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
                painter = painterResource(cartItem.menu.imageResId),
                contentDescription = cartItem.menu.name,
                tint = Color.Unspecified,
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            Spacer(modifier = Modifier.width(12.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = cartItem.menu.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1
                )
                Text(
                    text = "Rp ${cartItem.menu.price}",
                    fontSize = 14.sp,
                    color = Color(0xFF046D3C),
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = "Total: Rp ${cartItem.menu.price * cartItem.quantity}",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                IconButton(
                    onClick = onRemoveItem,
                    modifier = Modifier.size(32.dp)
                ) {
                    Icon(
                        imageVector = if (cartItem.quantity > 1) Icons.Default.Remove else Icons.Default.Delete,
                        contentDescription = "Remove",
                        tint = Color(0xFF046D3C),
                        modifier = Modifier.size(18.dp)
                    )
                }

                Text(
                    text = "${cartItem.quantity}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .background(
                            Color(0xFF046D3C),
                            RoundedCornerShape(6.dp)
                        )
                        .padding(horizontal = 12.dp, vertical = 4.dp),
                    color = Color.White
                )

                IconButton(
                    onClick = onAddItem,
                    modifier = Modifier.size(32.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add",
                        tint = Color(0xFF046D3C),
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun CartPage(
    modifier: Modifier = Modifier,
    onNavigateToHome: () -> Unit = {},
    onNavigateToMenu: () -> Unit = {},
    onNavigateToCart: () -> Unit = {},
    onNavigateToAccount: () -> Unit = {}
) {
    val cartItems = CartManager.cartItems
    val totalPrice = CartManager.getTotalPrice()

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
                .padding(16.dp)
        ) {

            Text(
                text = "Cart",
                fontWeight = FontWeight.ExtraBold,
                fontSize = 32.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            if (cartItems.isEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = "Empty Cart",
                        modifier = Modifier.size(80.dp),
                        tint = Color.Gray.copy(alpha = 0.5f)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Cart is Empty",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "Add some delicious items to your cart!",
                        fontSize = 16.sp,
                        color = Color.Gray,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(cartItems) { cartItem ->
                        CartItemRow(
                            cartItem = cartItem,
                            onAddItem = { CartManager.addToCart(cartItem.menu) },
                            onRemoveItem = { CartManager.removeFromCart(cartItem.menu) }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF046D3C)),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Total Items:",
                                fontSize = 16.sp,
                                color = Color.White
                            )
                            Text(
                                text = "${CartManager.getTotalItems()} items",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Total Price:",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                            Text(
                                text = "Rp $totalPrice",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            OutlinedButton(
                                onClick = { CartManager.clearCart() },
                                modifier = Modifier.weight(1f),
                                colors = ButtonDefaults.outlinedButtonColors(
                                    contentColor = Color.White
                                ),
                                border = androidx.compose.foundation.BorderStroke(1.dp, Color.White)
                            ) {
                                Text("Clear Cart")
                            }

                            Button(
                                onClick = { /* TODO: Implement checkout */ },
                                modifier = Modifier.weight(1f),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.White,
                                    contentColor = Color(0xFF046D3C)
                                )
                            ) {
                                Text(
                                    text = "Checkout",
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CartPagePreview() {
    CartPage()
}