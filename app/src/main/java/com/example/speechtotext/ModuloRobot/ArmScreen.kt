package com.example.speechtotext.ModuloRobot

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import kotlinx.coroutines.CoroutineScope
import java.io.OutputStream

@Composable
fun ArmScreen(
    coroutineScope: CoroutineScope,
    outputStream: MutableState<OutputStream?>,
    isConnected: MutableState<Boolean>
) {

}