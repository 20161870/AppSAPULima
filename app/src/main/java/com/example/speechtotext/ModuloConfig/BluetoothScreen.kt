package com.example.speechtotext.ModuloConfig

import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.TextToolbar
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.speechtotext.Navigation.Screen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException
import java.io.OutputStream
import java.util.UUID

@Composable
fun BluetoothScreen(
    coroutineScope: CoroutineScope,
    outputStream: MutableState<OutputStream?>,
    isConnected: MutableState<Boolean>
) {
    Column(
        Modifier.fillMaxWidth()
    ) {
        BluetoothFunctionality(coroutineScope, outputStream, isConnected)
        StatusBluetooth(isConnected)
    }

}

@SuppressLint("MissingPermission")
@Composable
fun BluetoothFunctionality(
    coroutineScope: CoroutineScope,
    outputStream: MutableState<OutputStream?>,
    isConnected: MutableState<Boolean>,
) {
    val context = LocalContext.current
    val bluetoothAdapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()
    val pairedDevices: List<BluetoothDevice> = bluetoothAdapter?.bondedDevices?.toList() ?: emptyList()
    var selectedDevice by remember { mutableStateOf<BluetoothDevice?>(null) }

    var socket: BluetoothSocket? by remember { mutableStateOf(null) }

    LaunchedEffect(selectedDevice) {
        coroutineScope.launch(Dispatchers.IO) {
            try {
                socket = selectedDevice?.createRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"))
                socket?.connect()
                outputStream.value = socket?.outputStream
                isConnected.value = true

            } catch (e: IOException) {
                //e.printStackTrace()
                isConnected.value = false
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Selecciona el dispositivo:",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(12.dp)
        )

        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(pairedDevices) { device ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { selectedDevice = device }
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(device.name ?: "Dispositivo Desconocido")
                }
            }
        }
    }
}

@Composable
fun StatusBluetooth(
    isConnected: MutableState<Boolean>
) {
    if(isConnected.value){
        Text(text = "Conectado")
    } else{
        Text(text = "Desonectado")
    }
}