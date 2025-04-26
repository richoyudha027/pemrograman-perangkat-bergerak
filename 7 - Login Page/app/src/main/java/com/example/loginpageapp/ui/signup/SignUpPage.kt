package com.example.loginpageapp.ui.signup

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import com.example.loginpageapp.ui.components.HeaderText
import com.example.loginpageapp.ui.components.LoginTextField


@Composable
fun SignUpPage(
    onSignUpClick: () -> Unit,
    onLoginClick: () -> Unit,
    onPolicyClick: () -> Unit,
    onPrivacyClick: () -> Unit,
){
    val (firstName, onFirstNameChange) = rememberSaveable { mutableStateOf("") }
    val (lastName, onLastNameChange) = remember { mutableStateOf("") }
    val (email, onEmailChange) = rememberSaveable { mutableStateOf("") }
    val (password, onPasswordChange) = rememberSaveable { mutableStateOf("") }
    val (confirmPassword, onConfirmPasswordChange) = rememberSaveable { mutableStateOf("") }
    val (agree, onAgreeChange) = rememberSaveable { mutableStateOf(false) }
    val context = LocalContext.current
    var isPasswordSame by remember { mutableStateOf(false) }
    val isFieldsNotEmpty = firstName.isNotEmpty() && lastName.isNotEmpty() &&
            email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty() && agree

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        AnimatedVisibility(isPasswordSame){
            Text(
                "Password is not Matching",
                color = MaterialTheme.colorScheme.error
            )
        }
        HeaderText(
            text = "Sign Up",
            modifier = Modifier
                .padding(vertical = 16.dp)
                .align(alignment = Alignment.Start)
        )
        LoginTextField(
            value = firstName,
            onValueChange = onFirstNameChange,
            labelText = "First Name",
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = Icons.Default.Person
        )
        Spacer(Modifier.height(16.dp))
        LoginTextField(
            value = lastName,
            onValueChange = onLastNameChange,
            labelText = "Last Name",
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = Icons.Default.Person
        )
        Spacer(Modifier.height(16.dp))
        LoginTextField(
            value = email,
            onValueChange = onEmailChange,
            labelText = "Email",
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = Icons.Default.Email
        )
        Spacer(Modifier.height(16.dp))
        LoginTextField(
            value = password,
            onValueChange = onPasswordChange,
            labelText = "Password",
            modifier = Modifier.fillMaxWidth(),
            keyboardType = KeyboardType.Password,
            leadingIcon = Icons.Default.Lock,
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(Modifier.height(16.dp))
        LoginTextField(
            value = confirmPassword,
            onValueChange = onConfirmPasswordChange,
            labelText = "Confirm Password",
            modifier = Modifier.fillMaxWidth(),
            keyboardType = KeyboardType.Password,
            leadingIcon = Icons.Default.Lock,
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(Modifier.height(16.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            val privacyText = "Privacy"
            val policyText = "Policy"
            val annotatedString = buildAnnotatedString {
                withStyle(SpanStyle(color = MaterialTheme.colorScheme.onBackground)) {
                   append("I Agree with")
                }
                append(" ")
                withStyle(SpanStyle(
                    color = Color(0xFF4A86E8),
                    fontWeight = FontWeight.Bold
                )) {
                    pushStringAnnotation(tag = privacyText, privacyText)
                    append(privacyText)
                }
                append(" and ")
                withStyle(SpanStyle(
                    color = Color(0xFF4A86E8),
                    fontWeight = FontWeight.Bold
                )) {
                    pushStringAnnotation(tag = policyText, policyText)
                    append(policyText)
                }
            }
            Checkbox(
                checked = agree,
                onCheckedChange = onAgreeChange,
                colors = CheckboxDefaults.colors(
                    checkedColor = Color(0xFF4A86E8),
                    checkmarkColor = Color.White
                )
            )
            ClickableText(annotatedString) {
                offset -> annotatedString.getStringAnnotations(offset, offset).forEach {
                    when(it.tag){
                        privacyText -> { onPrivacyClick() }
                        policyText -> { onPolicyClick() }
                    }
            }
            }
        }
        Spacer(Modifier.height(16.dp))
        Button(
            onClick = {
                isPasswordSame = password != confirmPassword
                if(!isPasswordSame){
                    onSignUpClick()
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = isFieldsNotEmpty,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF4A86E8)
            )
        ){
            Text(
                text = "Sign Up"
            )
        }
        Spacer(Modifier.height(16.dp))
        val signInText = "Sign In"
        val signInAnnotation = buildAnnotatedString {
            withStyle(SpanStyle(color = MaterialTheme.colorScheme.onBackground)) {
                append("Already have an account?")
            }
            append("  ")
            withStyle(SpanStyle(
                color = Color(0xFF4A86E8),
                fontWeight = FontWeight.Bold
            )) {
                pushStringAnnotation(signInText, signInText)
                append(signInText)
            }
        }
        ClickableText(
            signInAnnotation,
        ) { offset ->
            signInAnnotation.getStringAnnotations(offset, offset).forEach {
                if (it.tag == signInText) {
                    onLoginClick()
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PrevSignUp() {
    SignUpPage(
        onSignUpClick = {},
        onLoginClick = {},
        onPolicyClick = {},
        onPrivacyClick = {}
    )
}