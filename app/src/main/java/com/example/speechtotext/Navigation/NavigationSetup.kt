package com.example.speechtotext.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.speechtotext.MainScreen
import com.example.speechtotext.ModuloConfig.BluetoothScreen
import com.example.speechtotext.ModuloConfig.ConfigScreen
import com.example.speechtotext.ModuloConfig.WiFiScreen
import com.example.speechtotext.ModuloRobot.ArmScreen
import com.example.speechtotext.ModuloRobot.RobotScreen
import com.example.speechtotext.ModuloVoice.VoiceScreen
import com.example.speechtotext.ModuloVoice.VoiceViewModel
import java.io.OutputStream

@Composable
fun NavigationSetup(navController: NavHostController) {
    var coroutineScope = rememberCoroutineScope()
    val outputStream: MutableState<OutputStream?> = remember { mutableStateOf(null) }
    val isConnected = remember { mutableStateOf(false) }

    NavHost(
        navController,
        startDestination = Screen.Main.route
    ) {
        composable(Screen.Main.route) {
            MainScreen()
        }
        composable(Screen.Bluetooth.route) {
            BluetoothScreen(coroutineScope, outputStream, isConnected)
        }
        composable(Screen.WiFi.route) {
            WiFiScreen(coroutineScope, outputStream, isConnected)
        }
        composable(BottomNavItem.Configuration.route) {
            ConfigScreen(navController)
        }
        composable(BottomNavItem.Voice.route) {
            VoiceScreen(VoiceViewModel(), coroutineScope, outputStream, isConnected)
        }
        composable(BottomNavItem.Robot.route){
            RobotScreen(navController)
        }
        composable(Screen.Arm.route){
            ArmScreen(coroutineScope, outputStream, isConnected)
        }
    }
}