package ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import ui.BottomNavBar
import viewmodel.LoginViewmodel
import viewmodel.UsuarioViewModel


@OptIn(ExperimentalMaterial3Api::class, ExperimentalGridApi::class)
@Composable
fun ShippingScreen(viewModel: UsuarioViewModel, navController: NavController, loginViewModel: LoginViewmodel){

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                title = { Text("Pedidos") })
        },
        bottomBar = { BottomNavBar(navController) }
    ) {innerPadding ->
        Grid(
            config = {
                column(GridTrackSize.Auto)
                column(GridTrackSize.Auto)
                column(GridTrackSize.Auto)
                column(GridTrackSize.Auto)
                column(GridTrackSize.Auto)
                row(GridTrackSize.Auto)
                row(GridTrackSize.Auto)
                row(GridTrackSize.Auto)
                gap(row = 8.dp, column = 16.dp)

            },
            modifier = Modifier
                .padding(innerPadding)
                .border(0.5.dp, Color.Gray)
        ){
            Text(text = "", fontWeight = FontWeight.Bold)
            Text(text = "Cantidad", fontWeight = FontWeight.Bold)
            Text(text = "Fecha", fontWeight = FontWeight.Bold)
            Text(text = "Estado", fontWeight = FontWeight.Bold)
            Text(text = "Prioridad", fontWeight = FontWeight.Bold)

            // FlexBox Row
            Text(text = "Pedido 1", fontWeight = FontWeight.Bold)
            Text(text = "10 Unidades")
            Text(text = "14/11/2026")
            Text(text = "En transito")
            Text(text = "Media")

            // Grid Row
            Text(text = "Pedido 2", fontWeight = FontWeight.Bold)
            Text(text = "2 Unidades")
            Text(text = "15/11/2026")
            Text(text = "Recivido")
            Text(text = "Alta")

            Text(text = "Pedido 3", fontWeight = FontWeight.Bold)
            Text(text = "1 Unidad")
            Text(text = "16/11/2026")
            Text(text = "Preparando")
            Text(text = "Baja")
        }

    }
}


