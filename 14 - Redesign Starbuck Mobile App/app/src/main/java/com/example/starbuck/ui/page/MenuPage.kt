package com.example.starbuck.ui.page

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.starbuck.R
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import com.example.starbuck.ui.manager.CartManager
import kotlinx.coroutines.launch

@Composable
fun CoffeeTypeSelector(
    types: List<String>,
    selectedType: String,
    onTypeSelected: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        types.forEach { type ->
            val isSelected = type == selectedType
            if (isSelected) {
                Button(
                    onClick = { onTypeSelected(type) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF046D3C),
                        contentColor = Color.White
                    )
                ) {
                    Text(text = type)
                }
            } else {
                OutlinedButton(
                    onClick = { onTypeSelected(type) },
                    border = BorderStroke(1.dp, Color(0xFF046D3C)),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color(0xFF046D3C)
                    )
                ) {
                    Text(text = type)
                }
            }
        }
    }
}

@Composable
fun MenuItem(
    menu: Menu,
    onAddToCart: (Menu) -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
            .background(Color(0xFFF6F6F6), RoundedCornerShape(12.dp))
            .padding(16.dp)
    ){
        Icon(
            painter = painterResource(menu.imageResId),
            contentDescription = menu.name,
            tint = Color.Unspecified,
            modifier = Modifier
                .size(120.dp)
                .align(Alignment.CenterHorizontally)
                .clip(RoundedCornerShape(24.dp))
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = menu.name,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth(),
            maxLines = 1,
            overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
        )
        Text(
            text = "Rp ${menu.price}",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth(),
            color = Color(0xFF046D3C)
        )
        Spacer(Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            FloatingActionButton(
                onClick = { onAddToCart(menu) },
                modifier = Modifier.size(40.dp),
                containerColor = Color(0xFF046D3C),
                contentColor = Color.White,
                elevation = FloatingActionButtonDefaults.elevation(4.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = "Add to Cart",
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

@Composable
fun MenuGrid(
    menuList: List<Menu>,
    onAddToCart: (Menu) -> Unit
) {
    Column {
        for (i in menuList.indices step 2) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                MenuItem(
                    menu = menuList[i],
                    onAddToCart = onAddToCart,
                    modifier = Modifier.weight(1f)
                )
                if (i + 1 < menuList.size) {
                    MenuItem(
                        menu = menuList[i + 1],
                        onAddToCart = onAddToCart,
                        modifier = Modifier.weight(1f)
                    )
                } else {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Composable
fun MenuPage(
    modifier: Modifier = Modifier,
    onNavigateToHome: () -> Unit = {},
    onNavigateToMenu: () -> Unit = {},
    onNavigateToCart: () -> Unit = {},
    onNavigateToAccount: () -> Unit = {}
){
    var selectedType by remember { mutableStateOf("All") }
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    val scrollState = rememberScrollState()
    val menuList = listOf(
        Menu(R.drawable.caramel_macchiato, "Caramel Macchiato", "Macchiato", 42000),
        Menu(R.drawable.vanila_macchiato, "Vanilla Macchiato", "Macchiato", 44000),
        Menu(R.drawable.caffe_latte, "Caffè Latte", "Latte", 41000),
        Menu(R.drawable.hazelnut_latte, "Hazelnut Latte", "Latte", 43000),
        Menu(R.drawable.americano_classic, "Americano Classic", "Americano", 33000),
        Menu(R.drawable.long_black, "Long Black", "Americano", 35000),
        Menu(R.drawable.black_tea, "Black Tea", "Tea", 27000),
        Menu(R.drawable.lemon_tea, "Lemon Tea", "Tea", 29000),
        Menu(R.drawable.mango_sparkle, "Mango Sparkle", "Soda", 38000),
        Menu(R.drawable.strawberry_fizz, "Strawberry Fizz", "Soda", 38000)
    )

    val filteredMenu = if (selectedType == "All") menuList else menuList.filter { it.type == selectedType }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
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
        ) {
            Text(
                text = "Our Menu",
                fontWeight = FontWeight.ExtraBold,
                fontSize = 36.sp
            )
            Spacer(Modifier.height(16.dp))

            CoffeeTypeSelector(
                types = listOf("All", "Macchiato", "Latte", "Americano", "Tea", "Soda"),
                selectedType = selectedType,
                onTypeSelected = { selectedType = it }
            )
            Spacer(Modifier.height(16.dp))

            MenuGrid(
                menuList = filteredMenu,
                onAddToCart = { menu ->
                    CartManager.addToCart(menu)
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar("${menu.name} added to cart!")
                    }
                }
            )

            Spacer(Modifier.height(16.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MenuPagePrev(){
    MenuPage()
}

data class Menu(
    val imageResId: Int,
    val name: String,
    val type: String,
    val price: Int
)