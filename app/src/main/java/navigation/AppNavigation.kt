package navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.ActivityNavigator
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import ui.screens.ResumenScreen
import ui.screens.ResumenScreen2
import ui.screens.ResumenScreen3
import ui.screens.LoginScreen
import ui.screens.SelectScreen
import ui.screens.RegistroScreen
import viewmodel.UsuarioViewModel
import viewmodel.LoginViewmodel

@Composable
fun AppNavigation(){
    val navController = rememberNavController()

    val usuarioViewModel: UsuarioViewModel = viewModel()
    val loginViewmodel: LoginViewmodel = viewModel()

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
            LoginScreen(navController, usuarioViewModel, loginViewmodel)
        }
        composable("resumen"){
            ResumenScreen(usuarioViewModel, navController, loginViewmodel)
        }
        composable("resumen2"){
            ResumenScreen2(usuarioViewModel, navController)
        }
        composable("resumen3"){
            ResumenScreen3(usuarioViewModel, navController)
        }
    }
}
