package ui.screens

import LoginState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import viewmodel.UsuarioViewModel
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.graphics.drawable.toDrawable
import com.example.InsertCode.R
import ui.Screen
import viewmodel.LoginViewmodel


@OptIn(ExperimentalMaterial3Api::class)
@Composable


fun LoginScreen(
    navController: NavController,
    viewModel: UsuarioViewModel,
    loginViewmodel: LoginViewmodel
) {
    var isPasswordVisible by remember { mutableStateOf(false) }
    val estado by viewModel.estado.collectAsState()
    val loginState: LoginState by loginViewmodel.loginState.collectAsState()
    val errorMessage: String? = loginState.errorMessage
    val context = LocalContext.current
    var robotoFontFamily = FontFamily(
        Font(R.font.roboto_medium, FontWeight.Medium)
    )

    LaunchedEffect((loginState.loginStateType)) {
        if (loginState.loginStateType == LoginState.LoginStateType.LOGGED_IN) {
            navController.navigate(Screen.Home.rout) {
                popUpTo(Screen.Login.rout) { inclusive = true }
            }
        }
    }

    Box(Modifier.fillMaxSize()) {
        Column(
            Modifier
                .align(Alignment.Center)
                .padding(36.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)

        ) {


            Text(
                "Iniciar Sesion",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            OutlinedTextField(
                value = estado.correo,
                onValueChange = viewModel::onCorreoChange,
                label = { Text(text = "Correo") },
                isError = estado.errores.correo != null,
                supportingText = {
                    estado.errores.correo?.let {
                        Text(text = it, color = MaterialTheme.colorScheme.error)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = estado.clave,
                onValueChange = viewModel::onClaveChange,
                visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                label = { Text(text = "Contraseña") },
                isError = estado.errores.clave != null,
                supportingText = {
                    estado.errores.clave?.let {
                        Text(text = it, color = MaterialTheme.colorScheme.error)
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    val icon =
                        if (isPasswordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility
                    val descripcion =
                        if (isPasswordVisible) "Ocultar Contraseña" else "Mostar Constaseña"
                    IconButton({ isPasswordVisible = !isPasswordVisible }) {
                        androidx.compose.material.Icon(
                            imageVector = icon,
                            contentDescription = descripcion
                        )
                    }
                }
            )

            Button(
                onClick = {
                    viewModel.validarCredenciales { tipo ->
                        val destino = when (tipo) {
                            "admin" -> Screen.Home.rout
                            "supervisor" -> "resumen2"
                            "operador" -> "resumen3"
                            else -> Screen.Home.rout
                        }
                        destino?.let {
                            navController.navigate(it) {
                                popUpTo(Screen.Login.rout) { inclusive = true }
                            }
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Iniciar Sesion")
            }

            Row() {
                HorizontalDivider()
            }

            Button(
                onClick = {
                    loginViewmodel.login(context)
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(Color.White)
            ) {
                Icon(
                    painterResource(R.drawable.theme_neutral__show_text_no__shape_pill__platform_android_web),
                    contentDescription = "TEST"
                )
                Text(
                    "Acceder con Google",
                    fontFamily = robotoFontFamily,
                    modifier = Modifier.padding(16.dp)

                )
            }

            if (loginState.loginStateType == LoginState.LoginStateType.ERROR) {
                Text(
                    loginState.errorMessage ?: "Error al iniciar sesión",
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
    }

}


