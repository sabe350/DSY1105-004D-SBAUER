package ui.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Details
import androidx.compose.material.icons.filled.DirectionsCarFilled
import androidx.compose.material.icons.filled.Outbox
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ui.BottomNavBar
import ui.Screen
import viewmodel.LoginViewmodel
import viewmodel.UsuarioViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
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
                title = { Text("Inicio") })
        },
        bottomBar = { BottomNavBar(navController) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            ResumenPedidos(
                enviados = 1,
                pendientes = 1,
                recibidos = 1
            )
            ResumenAlertas(
                cant = 3
            )
        }
    }
}

@Composable
fun ResumenPedidos(
    enviados: Int,
    pendientes: Int,
    recibidos: Int,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth().padding(8.dp),
        shape = RoundedCornerShape(32.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),

    ) {
        Text("Resumen del dia", style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(horizontal = 25.dp), fontSize = 7.em)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp, horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            Icon(
                imageVector = Icons.Default.Outbox,
                contentDescription = null,
                modifier = Modifier.size(25.dp)
            )
            ContadorPedidos(titulo = "Enviados", valor = enviados)
            Icon(
                imageVector = Icons.Default.DirectionsCarFilled,
                contentDescription = null,
                modifier = Modifier.size(25.dp)
            )
            ContadorPedidos(titulo = "En transito", valor = pendientes)
            ContadorPedidos(titulo = "Recibidos", valor = recibidos)
        }
    }
}

@Composable
private fun ContadorPedidos(titulo: String, valor: Int) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = titulo,
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = valor.toString(),
        )
    }
}

@Composable
private fun ContadorAlertas(titulo: String, valor: Int) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = titulo,
        )
        Spacer(Modifier.width(8.dp))
        Text(
            text = valor.toString(),
        )
    }
}

@Composable
fun ResumenAlertas(
    cant : Int,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth().padding(8.dp),
        shape = RoundedCornerShape(32.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp, horizontal = 12.dp),

            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(Modifier.width(15.dp))
            Icon(
                imageVector = Icons.Default.Details,
                contentDescription = null,
                modifier = Modifier.size(40.dp)
            )
            Spacer(Modifier.width(15.dp))
            ContadorAlertas(titulo = "Alertas pendientes:", valor = cant)
        }
    }
}

