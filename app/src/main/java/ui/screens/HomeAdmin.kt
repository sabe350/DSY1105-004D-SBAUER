package ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import viewmodel.UsuarioViewModel

@Composable
fun ResumenScreen(viewModel: UsuarioViewModel){
        val estado by viewModel.estado.collectAsState()

    Column(Modifier.padding(10.dp)) {
        Text("HOME ADMIN", style = MaterialTheme.typography.headlineMedium)
        Text("Correo: ${estado.correo}")
        Text("Contraseña: ${"*".repeat(estado.clave.length)}")
        Text("Privilegios: ADMINISTRADOR")
    }
}