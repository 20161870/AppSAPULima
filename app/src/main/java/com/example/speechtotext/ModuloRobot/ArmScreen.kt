package com.example.speechtotext.ModuloRobot

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import kotlinx.coroutines.CoroutineScope
import java.io.OutputStream
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException


@SuppressLint("MissingPermission")
@OptIn(ExperimentalPermissionsApi::class)
@Composable
@RequiresApi(Build.VERSION_CODES.M)
fun ArmScreen(
    coroutineScope: CoroutineScope,
    outputStream: MutableState<OutputStream?>,
    isConnected: MutableState<Boolean>
) {
    // Movimientos predeterminados del brazo
    var messageToSend by remember { mutableStateOf("")}
    var messageToShow by remember { mutableStateOf("")}

    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        if(messageToShow !=  null){
            Text(
                text = messageToShow,
                fontSize = 24.sp,
                modifier = Modifier.align(CenterHorizontally)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                messageToSend = "1"
                messageToShow = "Mano Abierta"
            },
            modifier = Modifier.align(CenterHorizontally)
        ) {
            Text(
                text = "Mano abierta"
            )
        }

        Button(
            onClick = {
                messageToSend = "2"
                messageToShow = "Mano cerrada"
            },
            modifier = Modifier.align(CenterHorizontally)
        ) {
            Text(
                text = "Mano cerrada"
            )
        }

        Button(
            onClick = {
                messageToSend = "3"
                messageToShow = "Like"
            },
            modifier = Modifier.align(CenterHorizontally)
        ) {
            Text(
                text = "Like"
            )
        }
        Button(
            onClick = {
                messageToSend = "4"
                messageToShow = "Spiderman"
            },
            modifier = Modifier.align(CenterHorizontally)
        ) {
            Text(
                text = "Spiderman"
            )
        }
        Button(
            onClick = {
                messageToSend = "5"
                messageToShow = "Chevere"
            },
            modifier = Modifier.align(CenterHorizontally)
        ) {
            Text(
                text = "chevere"
            )
        }
        Button(
            onClick = {
                messageToSend = "6"
                messageToShow = "Aleatorio 1"
            },
            modifier = Modifier.align(CenterHorizontally)
        ) {
            Text(
                text = "Aleatorio 1"
            )
        }
        Button(
            onClick = {
                messageToSend = "7"
                messageToShow = "Secreto"
            },
            modifier = Modifier.align(CenterHorizontally)
        ) {
            Text(
                text = "Secreto"
            )
        }
        Button(
            onClick = {
                messageToSend = "8"
                messageToShow = "Aleatorio 2"
            },
            modifier = Modifier.align(CenterHorizontally)
        ) {
            Text(
                text = "Aleatorio 2"
            )
        }

        Button(
            onClick = {
                if(outputStream.value != null) {
                    coroutineScope.launch(Dispatchers.IO) {
                        try {
                            Log.i("mensaje prueba", "Se envió correctamente")
                            Log.i("mensaje prueba", messageToSend.toString())
                            outputStream?.value!!.write(messageToSend.toByteArray())
                            outputStream?.value!!.flush()
                            Log.i("mensaje prueba", "Se envió correctamente")
                            Log.i("mensaje prueba", messageToSend.toString())
                        }
                        catch(e: IOException) {
                            Log.i("mensaje prueba", "No se envió correctamente")
                            Log.i("mensaje prueba", messageToSend.toString())
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

@Composable
fun SendBluetooth() {
    
}