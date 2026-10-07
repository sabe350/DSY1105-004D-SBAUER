package navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ui.screens.ResumenScreen
import ui.screens.ResumenScreen2
import ui.screens.ResumenScreen3
import ui.screens.LoginScreen
import viewmodel.UsuarioViewModel

@Composable
fun AppNavigation(){
    val navController = rememberNavController()

    val usuarioViewModel: UsuarioViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "login"
    ){
        composable("login") {
            LoginScreen(navController, usuarioViewModel)
        }
        composable("resumen"){
            ResumenScreen(usuarioViewModel)
        }
        composable("resumen2"){
            ResumenScreen2(usuarioViewModel)
        }
        composable("resumen3"){
            ResumenScreen3(usuarioViewModel)
        }
    }
}
