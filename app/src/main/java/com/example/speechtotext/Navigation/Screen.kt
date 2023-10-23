package com.example.speechtotext.Navigation

sealed class Screen(val route: String) {
    object Main : Screen("MainScreen")
    object Configuration : Screen("ConfigScreen")
    object Voice : Screen("VoiceScreen")
    object Bluetooth : Screen("BluetoothScreen")
    object WiFi : Screen("WiFiScreen")
}