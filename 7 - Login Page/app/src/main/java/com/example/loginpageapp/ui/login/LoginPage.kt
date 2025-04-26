package com.example.loginpageapp.ui.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.example.loginpageapp.ui.components.HeaderText
import com.example.loginpageapp.ui.components.LoginTextField
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.example.loginpageapp.R
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CheckboxDefaults

@Composable
fun LoginPage(onLoginClick: () -> Unit, onSignUpClick: () -> Unit){
    val (userName, setUsername) = rememberSaveable { mutableStateOf("") }
    val (password, setPassword) = rememberSaveable { mutableStateOf("") }
    val (checked, onCheckedChange) = rememberSaveable { mutableStateOf(false) }
    val isFieldsEmpty = userName.isNotEmpty() && password.isNotEmpty()
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        HeaderText(
            text = "Login",
            modifier = Modifier
                .padding(vertical = 16.dp)
                .align(alignment = Alignment.Start)
        )
        LoginTextField(
            value = userName,
            onValueChange = setUsername,
            labelText = "Username",
            leadingIcon = Icons.Default.Person,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))
        LoginTextField(
            value = password,
            onValueChange = setPassword,
            labelText = "Password",
            leadingIcon = Icons.Default.Lock,
            modifier = Modifier.fillMaxWidth(),
            keyboardType = KeyboardType.Password,
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ){
                Checkbox(
                    checked = checked,
                    onCheckedChange = onCheckedChange,
                    colors = CheckboxDefaults.colors(
                        checkedColor = Color(0xFF4A86E8),
                        checkmarkColor = Color.White,
                        uncheckedColor = Color(0xFF4A86E8).copy(alpha = 0.6f)
                    )
                )
                Text("Remember me")
            }
            TextButton(onClick = {}) {
                Text(
                    text = "Forgot Password?",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF4A86E8)
                    )
                )
            }
        }
        Spacer(Modifier.height(8.dp))
        Button(
            onClick = onLoginClick,
            modifier = Modifier.fillMaxWidth(),
            enabled = isFieldsEmpty,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF4A86E8)
            )
        ){
            Text("Login")
        }
        Spacer(Modifier.weight(1f))
        AlternativeLoginOptions(
            onIconClick = { index ->
                when (index){
                    0 -> {
                        Toast.makeText(context, "Google Login Click", Toast.LENGTH_SHORT).show()
                    }
                    1 -> {
                        Toast.makeText(context, "Facebook Login Click", Toast.LENGTH_SHORT).show()
                    }
                }
            },
            onSignUpClick = onSignUpClick,
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(align = Alignment.BottomCenter)
        )
    }

}

@Composable
fun AlternativeLoginOptions(
    onIconClick: (index: Int) -> Unit,
    onSignUpClick: () -> Unit,
    modifier: Modifier = Modifier
){
    val iconList = listOf(
        R.drawable.google,
        R.drawable.facebook
    )
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text("Or Sign in With")
        Spacer(Modifier.height(8.dp))
        Row(horizontalArrangement = Arrangement.Center){
            iconList.forEachIndexed { index, iconName ->
                Image(
                    painter = painterResource(iconName),
                    contentDescription = "Alternative Login",
                    modifier = Modifier
                        .size(32.dp)
                        .clickable{
                            onIconClick(index)
                        }.clip(CircleShape)
                )
                if (index < iconList.size - 1) {
                    Spacer(Modifier.width(16.dp))
                }
            }
        }
        Spacer(Modifier.height(8.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text("Don't have an Account?")
            Spacer(Modifier.height(8.dp))
            TextButton(onClick = onSignUpClick) {
                Text(
                    text = "Sign Up",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF4A86E8)
                    )
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun Show(){
    LoginPage(onLoginClick = {}, onSignUpClick = {})
}
