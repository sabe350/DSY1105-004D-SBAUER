package ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.InsertCode.R
import ui.BottomNavBar
import ui.Screen
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

        Box(Modifier.padding(horizontal = 50.dp, vertical = 10.dp)) {
            Card(Modifier.padding(innerPadding)) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, Color.Unspecified, RoundedCornerShape(12.dp)),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .padding(vertical = 10.dp)
                    ) {
                        Text("${estado.correo}",
                            style = MaterialTheme.typography.titleMedium )
                    }
                    Button(
                        onClick = {
                            viewModel.logOut()
                            loginViewModel.logOut()
                            navController.navigate(Screen.Select.rout){popUpTo(navController.graph.id) {inclusive = true} }
                        },
                        modifier = Modifier.width(200.dp).padding(10.dp)
                    ) {
                        Text("Cerrar Sesion")
                    }
                }
                }
            }

        }
    }



