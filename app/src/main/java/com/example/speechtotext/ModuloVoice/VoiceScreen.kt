package com.example.speechtotext.ModuloVoice

import android.Manifest
import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException
import java.io.OutputStream
import java.util.UUID


@SuppressLint("MissingPermission")
@OptIn(ExperimentalPermissionsApi::class)
@Composable
@RequiresApi(Build.VERSION_CODES.M)
fun VoiceScreen(
    viewModel: VoiceViewModel,
    coroutineScope : CoroutineScope,
    outputStream: MutableState<OutputStream?>,
    isConnected: MutableState<Boolean>
) {
    val permissionState = rememberPermissionState(
        permission = Manifest.permission.RECORD_AUDIO
    )
    SideEffect {
        permissionState.launchPermissionRequest()
    }

    val speechRecognizerLauncher = rememberLauncherForActivityResult(
        contract = SpeechRecognizerContract(),
        onResult = {
            viewModel.changeTextValue(it.toString())
        }
    )

    var messageToSend by remember { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(16.dp).
                fillMaxSize()
    ) {
        if(viewModel.state.text != null){
            messageToSend = viewModel.state.text!!.substring(1, viewModel.state.text!!.length-1)
            Text(
                text = messageToSend,
                fontSize = 24.sp,
                modifier = Modifier.align(CenterHorizontally)
            )

        }
        Spacer(modifier = Modifier.height(20.dp))

        // Boton de Speech-To-Text
        Button(
            onClick = {
                if(permissionState.status.isGranted){
                    speechRecognizerLauncher.launch(Unit)
                }else
                    permissionState.launchPermissionRequest()
            },
            modifier = Modifier.align(CenterHorizontally)
        ) {
            Text(
                text = "Hablar"
            )
        }

        // Boton para enviar mensaje del Speech-To-Text
        Button(
            onClick = {
                if (outputStream.value != null) {
                    coroutineScope.launch(Dispatchers.IO) {
                        try {
                            outputStream?.value!!.write(messageToSend.toByteArray())
                            outputStream?.value!!.flush()
                        } catch (e: IOException) {
                            //e.printStackTrace()
                        }
                    }
                }
            },
            modifier = Modifier.align(CenterHorizontally)
        ) {
            Text("Enviar Mensaje")
        }
    }
}