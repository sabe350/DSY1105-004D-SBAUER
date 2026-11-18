package ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalGridApi
import androidx.compose.foundation.layout.Grid
import androidx.compose.foundation.layout.GridTrackSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessAlarms
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavController
import ui.BottomNavBar
import viewmodel.LoginViewmodel
import viewmodel.UsuarioViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShippingScreen(
    viewModel: UsuarioViewModel,
    navController: NavController,
) {
    val estado by viewModel.estado.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                title = { Text("Pedidos") }
            )
        },
        bottomBar = { BottomNavBar(navController) }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(1) { index ->
                PedidoCard(
                    titulo = "Pedido ${index + 1}",
                    estado = "En Transito",
                    cliente = "Cliente 1",
                    producto = "Producto 1",
                    cantidad = "25 Unidades",
                    prioridadInicial = "Media"
                )
            }
        }
    }
}

@Composable
fun PedidoCard(
    titulo: String,
    estado: String,
    cliente: String,
    producto: String,
    cantidad: String,
    prioridadInicial: String,
    modifier: Modifier = Modifier
) {
    var prioridad by remember { mutableStateOf(prioridadInicial) }

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(titulo, style = MaterialTheme.typography.titleLarge)
                    Text(estado, style = MaterialTheme.typography.titleMedium)
                }
                Text(cliente, style = MaterialTheme.typography.bodyMedium)
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text(producto, style = MaterialTheme.typography.bodyMedium)
                    Text(cantidad, style = MaterialTheme.typography.bodyMedium)
                }
            }

            // Columna derecha: prioridad
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Prioridad", style = MaterialTheme.typography.titleMedium)
                PrioridadSelector(
                    seleccionada = prioridad,
                    onSeleccion = { prioridad = it }
                )
            }
        }
    }
}

@Composable
private fun PrioridadSelector(
    seleccionada: String,
    onSeleccion: (String) -> Unit
) {
    var expandido by remember { mutableStateOf(false) }
    val opciones = listOf("Alta", "Media", "Baja")

    Box {
        Row(
            modifier = Modifier
                .border(1.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(12.dp))
                .clickable { expandido = true }
                .padding(horizontal = 12.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Default.ArrowDropDown, contentDescription = "Cambiar prioridad")
            Spacer(Modifier.width(8.dp))
            Text(seleccionada, style = MaterialTheme.typography.bodyMedium)
        }

        DropdownMenu(
            expanded = expandido,
            onDismissRequest = { expandido = false }
        ) {
            opciones.forEach { opcion ->
                DropdownMenuItem(
                    text = { Text(opcion) },
                    onClick = {
                        onSeleccion(opcion)
                        expandido = false
                    }
                )
            }
        }
    }
}





