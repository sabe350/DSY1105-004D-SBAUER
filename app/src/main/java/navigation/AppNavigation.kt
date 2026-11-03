package navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.ActivityNavigator
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ui.screens.ResumenScreen
import ui.screens.ResumenScreen2
import ui.screens.ResumenScreen3
import ui.screens.LoginScreen
import ui.screens.SelectScreen
import ui.screens.RegistroScreen
import viewmodel.UsuarioViewModel

@Composable
fun AppNavigation(){
    val navController = rememberNavController()

    val usuarioViewModel: UsuarioViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "select",
    ){
        composable("select") {
            SelectScreen(navController)
        }
        composable("register") {
            RegistroScreen(navController, usuarioViewModel)
        }
        composable("login") {
            LoginScreen(navController, usuarioViewModel)
        }
        composable("resumen"){
            ResumenScreen(usuarioViewModel, navController)
        }
        composable("resumen2"){
            ResumenScreen2(usuarioViewModel, navController)
        }
        composable("resumen3"){
            ResumenScreen3(usuarioViewModel, navController)
        }
    }
}
