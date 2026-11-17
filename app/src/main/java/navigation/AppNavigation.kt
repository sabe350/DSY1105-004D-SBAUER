package navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph
import ui.Screen
import ui.screens.AlertScreen
import ui.screens.ResumenScreen2
import ui.screens.ResumenScreen3
import ui.screens.HomeScreenAdmin
import ui.screens.LoginScreen
import ui.screens.ProfileScreen
import ui.screens.SelectScreen
import ui.screens.RegistroScreen
import ui.screens.RouteScreen
import ui.screens.ShippingScreen
import viewmodel.UsuarioViewModel
import viewmodel.LoginViewmodel

@SuppressLint("ComposableDestinationInComposeScope")
@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    val usuarioViewModel: UsuarioViewModel = viewModel()
    val loginViewmodel: LoginViewmodel = viewModel()

    NavHost(
        navController = navController,
        startDestination = Screen.Select.rout
        ) {
            composable(Screen.Select.rout) {
                SelectScreen(navController)
            }
            composable(Screen.Register.rout) {
                RegistroScreen(navController, usuarioViewModel)
            }
            composable(Screen.Login.rout) {
                LoginScreen(navController, usuarioViewModel, loginViewmodel)
            }
            composable(Screen.Home.rout) {
                HomeScreenAdmin(usuarioViewModel, navController)
            }
            composable("resumen2") {
                ResumenScreen2(usuarioViewModel, navController)
            }
            composable("resumen3") {
                ResumenScreen3(usuarioViewModel, navController)
            }
            composable(Screen.Profile.rout) {
                ProfileScreen(usuarioViewModel, navController, loginViewmodel)
            }
            composable(Screen.Shipping.rout) {
                ShippingScreen(usuarioViewModel, navController, loginViewmodel)
            }
            composable(Screen.Routes.rout) {
                RouteScreen(usuarioViewModel, navController, loginViewmodel)
            }
            composable(Screen.Alert.rout) {
                AlertScreen(usuarioViewModel, navController, loginViewmodel)
            }
        }
    }


