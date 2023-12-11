package com.example.speechtotext.ModuloRobot

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.speechtotext.Navigation.Screen

@Composable
fun RobotScreen(
    navController: NavController
) {
    // Opciones para las piezas del robot InMoov
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
                    navController.navigate(Screen.Arm.route) {
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
                    Text(text = "Brazo")
                }
            }
        }
    }
}