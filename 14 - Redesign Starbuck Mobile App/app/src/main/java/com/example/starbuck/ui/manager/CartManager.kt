package com.example.starbuck.ui.manager

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.starbuck.ui.page.Menu

data class CartItem(
    val menu: Menu,
    val quantity: Int
)

object CartManager {
    private var _cartItems by mutableStateOf(listOf<CartItem>())
    val cartItems: List<CartItem> get() = _cartItems

    fun addToCart(menu: Menu) {
        val existingItem = _cartItems.find { it.menu.name == menu.name }
        if (existingItem != null) {
            _cartItems = _cartItems.map {
                if (it.menu.name == menu.name) {
                    it.copy(quantity = it.quantity + 1)
                } else {
                    it
                }
            }
        } else {
            _cartItems = _cartItems + CartItem(menu, 1)
        }
    }

    fun removeFromCart(menu: Menu) {
        val existingItem = _cartItems.find { it.menu.name == menu.name }
        if (existingItem != null) {
            if (existingItem.quantity > 1) {
                _cartItems = _cartItems.map {
                    if (it.menu.name == menu.name) {
                        it.copy(quantity = it.quantity - 1)
                    } else {
                        it
                    }
                }
            } else {
                _cartItems = _cartItems.filter { it.menu.name != menu.name }
            }
        }
    }

    fun clearCart() {
        _cartItems = emptyList()
    }

    fun getTotalPrice(): Int {
        return _cartItems.sumOf { it.menu.price * it.quantity }
    }

    fun getTotalItems(): Int {
        return _cartItems.sumOf { it.quantity }
    }
}