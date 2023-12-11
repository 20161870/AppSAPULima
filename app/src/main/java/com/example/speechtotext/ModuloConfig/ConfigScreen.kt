package com.example.speechtotext.ModuloConfig

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.speechtotext.Navigation.Screen

@Composable
fun ConfigScreen(
    navController: NavController
) {
    // Opciones de la pantalla Configuración
    // ** Para agregar otra opción, copiar otro bloque de codigo desde "Surface" **
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ){
            Text(text = "Selecciona una opción",
            modifier = Modifier.padding(10.dp))
        }

        Surface(
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .padding(
                    vertical = 4.dp,
                    horizontal = 8.dp
                )
                .clickable {
                    navController.navigate(Screen.Bluetooth.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
        ){
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = "Bluetooth")
                }
            }
        }

        Surface(
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .padding(
                    vertical = 4.dp,
                    horizontal = 8.dp
                )
                .clickable {
                    navController.navigate(Screen.WiFi.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
        ){
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = "WiFi")
                }
            }
        }
    }
}