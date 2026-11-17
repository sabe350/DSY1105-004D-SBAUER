package ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ui.BottomNavBar
import viewmodel.LoginViewmodel
import viewmodel.UsuarioViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(viewModel: UsuarioViewModel, navController: NavController, loginViewModel: LoginViewmodel){
    val estado by viewModel.estado.collectAsState()
    
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
            ),
            title = { Text("Perfil") })
    },
        bottomBar = { BottomNavBar(navController) }
    ) { innerPadding ->
            Text("Correo: ${estado.correo}")
        Box() {
            Column(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(innerPadding)

            ) {
                Button(
                    onClick = {
                        loginViewModel.logOut()
                        viewModel.logOut()
                        navController.navigate("select") {
                            popUpTo(navController.graph.id) {
                                inclusive = true
                            }
                        }
                    }
                ) {
                    Text("Cerrar Sesion")
                }
            }
        }
    }
}

