package ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.navigation.compose.rememberNavController
import ui.BottomNavBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(){
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("TEST") })
        },
        bottomBar = { BottomNavBar(navController) }
    )   { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            )   {
                    Text(text = "Bienvenido")
                    Button(onClick = {}){
                        Text("Boton")
                    }


            }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview(){
    HomeScreen()
}


