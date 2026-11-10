package ui.screens

import android.graphics.Outline
import android.graphics.drawable.Icon
import android.widget.EditText
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFrom
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.*
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.HorizontalDivider
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.text.googlefonts.Font
import androidx.credentials.Credential
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import com.example.InsertCode.R
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential

val googleIdOption = GetGoogleIdOption.Builder()
    .setServerClientId(getString(com.firebase.ui.auth.R.string.default_web_client_id))
    .setFilterByAuthorizedAccounts(true)
    .build()

var request = GetCredentialRequest.Builder()
    .addCredentialOption(googleIdOption)
    .build()

private fun handleSignIn(credential: Credential){
    if (credential is CustomCredential && credential.type == TYPE_GOOGLE_ID_TOKEN_CREDENTIAL){
        val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
        firebaseAuthWithGoogle(googleIdTokenCredential.idToken)
    }else{
        Log.w(TAG, "Credential is not of type Google ID")
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun LoginScreen(
    navController: NavController,
    viewModel: UsuarioViewModel
) {
    var isPasswordVisible by remember { mutableStateOf(false) }
    val estado by viewModel.estado.collectAsState()
    var robotoFontFamily = FontFamily(
        Font(R.font.roboto_medium, FontWeight.Medium)
    )


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

                    if (viewModel.validarInicioSesion() && viewModel.validarCredenciales() && viewModel.tipoInicio() == "admin") {
                        navController.navigate("resumen"){popUpTo("login"){inclusive = true } }
                    }else
                        if (viewModel.validarInicioSesion() && viewModel.validarCredenciales() && viewModel.tipoInicio() == "supervisor") {
                            navController.navigate("resumen2"){popUpTo("login"){inclusive = true } }
                        } else
                            if (viewModel.validarCredenciales() && viewModel.validarCredenciales() && viewModel.tipoInicio() == "operador") {
                                navController.navigate("resumen3"){popUpTo("login"){inclusive = true } }
                            }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Iniciar Sesion")
            }

            Row(){
                HorizontalDivider()
            }

            Button(onClick = {null}, modifier = Modifier.fillMaxWidth()) {
                Text("Acceder con Google", fontFamily = robotoFontFamily, modifier = Modifier
                    .padding(16.dp))
            }
        }
    }
}

private fun String.get(index: String) {}
