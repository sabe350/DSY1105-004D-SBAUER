package ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalGridApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.InsertCode.R
import ui.BottomNavBar
import viewmodel.LoginViewmodel
import viewmodel.UsuarioViewModel

data class DropdownItemRoute(
    val title: String
)

@OptIn(ExperimentalMaterial3Api::class, ExperimentalGridApi::class)
@Composable
fun RouteScreen(viewModel: UsuarioViewModel, navController: NavController, loginViewModel: LoginViewmodel){
    var expanded by remember { mutableStateOf(false) }
    var selectedItem by remember {mutableStateOf(DropdownItemRoute("En Espera"))}
    val itemList = listOf(
        DropdownItemRoute("En Transito"),
        DropdownItemRoute("Recibido"),
        DropdownItemRoute("En Espera")
    )
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                title = { Text("Rutas") })
        },
        bottomBar = { BottomNavBar(navController) }
    )
    { innerPadding ->
        Box() {
            Column(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(innerPadding)
                    .border(0.5.dp, Color.LightGray),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                ) {
                    Image(
                        painterResource(R.drawable.mapa),
                        contentDescription = null,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                Box() {
                    Text("Pedido 1")
                }
                Box() {
                    Text("Central -> Calle Falsa 123")
                }
                Box() {
                    ExposedDropdownMenuBox(
                        modifier = Modifier
                            .padding(horizontal = 20.dp, vertical = 0.dp)
                            .clip(RoundedCornerShape(10.dp)),
                        expanded = expanded,
                        onExpandedChange = {
                            expanded = !expanded
                        }
                    ) {
                        Row(

                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            TextField(
                                modifier = Modifier
                                    .menuAnchor(),
                                value = selectedItem.title,
                                onValueChange = { },
                                readOnly = true,
                                trailingIcon = {
                                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                                }
                            )
                        }

                        ExposedDropdownMenu(
                            expanded = expanded,
                            onDismissRequest = {
                                expanded = false
                            }
                        ) {
                            itemList.forEachIndexed { index, item ->
                                DropdownMenuItem(
                                    text = {
                                        Row(
                                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Text(item.title)
                                        }
                                    },
                                    onClick = {
                                        selectedItem = itemList[index]
                                        expanded = false
                                    },
                                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                                )
                            }
                        }

                    }
                }
            }
            }
        }
    }
