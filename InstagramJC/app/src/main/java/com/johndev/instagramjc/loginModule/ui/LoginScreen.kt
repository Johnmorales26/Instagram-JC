package com.johndev.instagramjc.loginModule.ui

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.johndev.instagramjc.R

@Composable
fun LoginScreen(loginViewModel: LoginViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(getGradientBrush())
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Header(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
            Body(
                loginViewModel,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
            Footer(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
        }
    }
}

fun getGradientBrush(): Brush {
    return Brush.verticalGradient(
        colors = listOf(Color(0xFFFFFFFF), Color(0xFFF4F4F4), Color(0xFFFFFFFF)),
        startY = 0f,
        endY = 2500f,
    )
}

@Composable
fun Footer(modifier: Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Bottom
    ) {
        OutlinedButton(
            shape = RoundedCornerShape(4.dp),
            modifier = Modifier.fillMaxWidth(),
            onClick = { },
            colors = ButtonDefaults.outlinedButtonColors(

            )
        ) {
            Text(text = "Crear cuenta nueva")
        }
        Spacer(modifier = Modifier.size(24.dp))
        SignUp()
        Spacer(modifier = Modifier.size(24.dp))
    }
}

@Composable
fun SignUp() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier.size(16.dp),
            painter = painterResource(id = R.drawable.ic_meta),
            contentDescription = null
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            text = "Meta",
            fontSize = 12.sp,
            color = Color(0xFFB5B5B5)
        )
    }
}

@Composable
fun Body(loginViewModel: LoginViewModel, modifier: Modifier) {
    val email: String by loginViewModel.email.observeAsState(initial = "")
    val password: String by loginViewModel.password.observeAsState(initial = "")
    val isLoginEnable: Boolean by loginViewModel.isLoginEnable.observeAsState(initial = false)
    Column(modifier = modifier) {
        Email(email) { email ->
            loginViewModel.onLoginChanged(email = email, password = password)
        }
        Spacer(modifier = Modifier.size(4.dp))
        Password(password) { password ->
            loginViewModel.onLoginChanged(email = email, password = password)
        }
        Spacer(modifier = Modifier.size(8.dp))
        LoginButton(isLoginEnable)
        Spacer(modifier = Modifier.size(4.dp))
        ForgotPassword()
    }
}

@Composable
fun LoginButton(loginEnable: Boolean) {
    Button(
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF4EA8E9),
            disabledContainerColor = Color(0xFF78C8F9),
            contentColor = Color.White,
            disabledContentColor = Color.White
        ),
        enabled = loginEnable,
        onClick = { }
    ) {
        Text(text = "Iniciar sesión")
    }
}

@Composable
fun ForgotPassword() {
    TextButton(
        modifier = Modifier.fillMaxWidth(),
        onClick = { }
    ) {
        Text(text = "¿Olvidaste tu contraseña?")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Password(password: String, onTextChange: (String) -> Unit) {
    var passwordVisibility by remember { mutableStateOf(false) }
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = password,
        onValueChange = { onTextChange(it) },
        placeholder = { Text(text = "Contraseña") },
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFFB2B2B2),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            containerColor = Color(0xFFFAFAFA)
        ),
        maxLines = 1,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                Icon(
                    painter = if (passwordVisibility) {
                        painterResource(id = R.drawable.ic_visibility)
                    } else {
                        painterResource(id = R.drawable.ic_visibility_off)
                    }, contentDescription = "Show Password"
                )
            }
        },
        visualTransformation = if (passwordVisibility) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Email(email: String, onTextChange: (String) -> Unit) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = email,
        onValueChange = { onTextChange(it) },
        placeholder = { Text(text = "Nombre de usuario, correo o celular") },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFFB2B2B2),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            containerColor = Color(0xFFFFFFFF)
        )
    )
}

@Composable
fun Header(modifier: Modifier) {
    val activity = LocalContext.current as Activity
    Box(modifier = modifier) {
        IconButton(
            modifier = Modifier.align(Alignment.TopEnd),
            onClick = { activity.finish() }
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "close app"
            )
        }
        Text(
            text = "Español",
            style = MaterialTheme.typography.labelSmall,
            color = Color(0xFFB5B5B5),
            modifier = Modifier.align(Alignment.TopCenter),
        )
        Image(
            modifier = Modifier
                .align(Alignment.Center)
                .size(48.dp),
            painter = painterResource(id = R.drawable.ic_instagram),
            contentDescription = null
        )
    }
}
