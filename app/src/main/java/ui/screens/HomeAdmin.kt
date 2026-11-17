package ui.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ui.BottomNavBar
import viewmodel.UsuarioViewModel

data class DropdownItem(
    val title: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenAdmin(viewModel: UsuarioViewModel, navController: NavController){
    var expanded by remember { mutableStateOf(false) }
    var expanded2 by remember { mutableStateOf(false) }
    var expanded3 by remember { mutableStateOf(false) }
    var selectedItem by remember {mutableStateOf(DropdownItem("ADMIN"))}
    var selectedItem2 by remember {mutableStateOf(DropdownItem("SUPERVISOR"))}
    var selectedItem3 by remember {mutableStateOf(DropdownItem("OPERADOR"))}

    val itemList = listOf(
        DropdownItem("ADMIN"),
        DropdownItem("SUPERVISOR"),
        DropdownItem("OPERADOR")
    )


    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                title = { Text("Gestion de Usuarios") })
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
            TopAppBar(title = {Text("Usuarios")})
            Row(Modifier
                .border(0.5.dp, Color.LightGray, CircleShape)
                .padding(horizontal = 10.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically
                ) {
                Box(
                ) {
                    Text("admin@guardian.net", fontWeight = FontWeight.Bold, fontSize = 10.sp)
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
            Row(Modifier
                .border(0.5.dp, Color.LightGray, CircleShape)
                .padding(horizontal = 10.dp, vertical = 10.dp)
                .clip(RoundedCornerShape(10.dp)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                ) {
                    Text("supervisor@guardian.net", fontWeight = FontWeight.Bold, fontSize = 10.sp)
                }
                Box() {
                    ExposedDropdownMenuBox(
                        modifier = Modifier
                            .padding(horizontal = 10.dp, vertical = 0.dp)
                            .width(200.dp)
                            .clip(RoundedCornerShape(10.dp)),
                        expanded = expanded2,
                        onExpandedChange = {
                            expanded2 = !expanded2
                        }
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            TextField(
                                modifier = Modifier.menuAnchor(),
                                value = selectedItem2.title,
                                onValueChange = { },
                                readOnly = true,
                                trailingIcon = {
                                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded2)
                                }
                            )
                        }

                        ExposedDropdownMenu(
                            expanded = expanded2,
                            onDismissRequest = {
                                expanded2 = false
                            }
                        ) {
                            itemList.forEachIndexed { index, item2 ->
                                DropdownMenuItem(
                                    text = {
                                        Row(
                                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Text(item2.title)
                                        }
                                    },
                                    onClick = {
                                        selectedItem2 = itemList[index]
                                        expanded2 = false
                                    },
                                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                                )
                            }
                        }

                    }
                }
            }
            Row(Modifier
                .border(0.5.dp, Color.LightGray, CircleShape)
                .padding(horizontal = 10.dp, vertical = 10.dp)
                .clip(RoundedCornerShape(10.dp)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                ) {
                    Text("operador@guardian.net", fontWeight = FontWeight.Bold, fontSize = 10.sp)
                }
                Box() {
                    ExposedDropdownMenuBox(
                        modifier = Modifier
                            .padding(horizontal = 20.dp, vertical = 0.dp)
                            .width(200.dp)
                            .clip(RoundedCornerShape(10.dp)),
                        expanded = expanded3,
                        onExpandedChange = {
                            expanded3 = !expanded3
                        }
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            TextField(
                                modifier = Modifier.menuAnchor(),
                                value = selectedItem3.title,
                                onValueChange = { },
                                readOnly = true,
                                trailingIcon = {
                                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded3)
                                }
                            )
                        }

                        ExposedDropdownMenu(
                            expanded = expanded3,
                            onDismissRequest = {
                                expanded3 = false
                            }
                        ) {
                            itemList.forEachIndexed { index, item3 ->
                                DropdownMenuItem(
                                    text = {
                                        Row(
                                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Text(item3.title)
                                        }
                                    },
                                    onClick = {
                                        selectedItem3 = itemList[index]
                                        expanded3 = false
                                    },
                                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                                )
                            }
                        }

                    }
                }
            }
            Button(onClick = {null}){
                Text("AÑADIR USUARIO")
            }
        }
    }
}


