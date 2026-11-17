package ui.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Details
import androidx.compose.material3.Button
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ui.BottomNavBar
import ui.Screen
import viewmodel.LoginViewmodel
import viewmodel.UsuarioViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlertScreen(viewModel: UsuarioViewModel, navController: NavController, loginViewModel: LoginViewmodel){
    val estado by viewModel.estado.collectAsState()


    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                title = { Text("Alertas") })
        },
        bottomBar = { BottomNavBar(navController) }
    ) { innerPadding ->
        Box(Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Box(Modifier
                    .border(0.5.dp, Color.LightGray, CircleShape)
                    .fillMaxWidth()
                    .padding(15.dp),) {
                    Text(
                        "Alerta 1",
                        modifier = Modifier.padding(10.dp)
                    )
                }


                Box(Modifier
                    .border(0.5.dp, Color.LightGray, CircleShape)
                    .fillMaxWidth()
                    .padding(15.dp),) {
                    Text(
                        "Alerta 1",
                        modifier = Modifier
                            .padding(10.dp)
                    )
                }
                Box(Modifier
                    .border(0.5.dp, Color.LightGray, CircleShape)
                    .fillMaxWidth()
                    .padding(15.dp),) {
                    Text(
                        "Alerta 1",
                        modifier = Modifier.padding(10.dp)
                    )
                }
                Box(Modifier
                    .border(0.5.dp, Color.LightGray, CircleShape)
                    .fillMaxWidth()
                    .padding(15.dp),) {

                    Text(
                        "Alerta 1",
                        modifier = Modifier.padding(10.dp)
                    )
                }
            }
        }
    }
}

