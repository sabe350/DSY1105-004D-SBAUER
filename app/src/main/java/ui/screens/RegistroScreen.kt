package ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import viewmodel.UsuarioViewModel

@Composable
fun RegistroScreen(
    navController: NavController,
    viewModel: UsuarioViewModel
){
    var isPasswordVisible by remember { mutableStateOf(false) }
    var isPassword2Visible by remember { mutableStateOf(false) }
    val estado by viewModel.estado.collectAsState()

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            "Registrarse",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        OutlinedTextField(
            value = estado.nombre,
            onValueChange = viewModel::onNombreChange,
            label = { Text(text = "Nombre")},
            isError = estado.errores.nombre != null,
            supportingText = {
                estado.errores.nombre?.let{
                    Text(text=it, color = MaterialTheme.colorScheme.error)
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = estado.correo,
            onValueChange = viewModel::onCorreoChange,
            label = { Text(text = "Correo")},
            isError = estado.errores.correo != null,
            supportingText = {
                estado.errores.correo?.let{
                    Text(text=it, color = MaterialTheme.colorScheme.error)
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

        OutlinedTextField(
            value = estado.clave2,
            onValueChange = viewModel::onClave2Change,
            visualTransformation = if (isPassword2Visible) VisualTransformation.None else PasswordVisualTransformation(),
            label = { Text(text = "Repetir Contraseña") },
            isError = estado.errores.clave2 != null,
            supportingText = {
                estado.errores.clave2?.let {
                    Text(text = it, color = MaterialTheme.colorScheme.error)
                }
            },
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                val icon =
                    if (isPassword2Visible) Icons.Default.VisibilityOff else Icons.Default.Visibility
                val descripcion =
                    if (isPassword2Visible) "Ocultar Contraseña" else "Mostar Constaseña"
                IconButton({ isPassword2Visible = !isPassword2Visible }) {
                    androidx.compose.material.Icon(
                        imageVector = icon,
                        contentDescription = descripcion
                    )
                }
            }
        )


        OutlinedTextField(
            value = estado.direccion,
            onValueChange = viewModel::onDireccionChange,
            label = { Text(text = "Direccion")},
            isError = estado.errores.direccion != null,
            supportingText = {
                estado.errores.direccion?.let{
                    Text(text=it, color = MaterialTheme.colorScheme.error)
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        Row(verticalAlignment = Alignment.CenterVertically){
            Checkbox(
                checked = estado.aceptaTerminos,
                onCheckedChange = viewModel::onAceptarTerminosChange
            )
            Spacer(Modifier.width(8.dp))
            Text("Acepto los terminos y condiciones")
        }

        Button(
            onClick = {
                if (viewModel.validarFomulario()){
                    viewModel.Regsitro()
                    navController.navigate("select"){popUpTo(navController.graph.id) {inclusive = true} }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Registrarse")
        }


    }
}