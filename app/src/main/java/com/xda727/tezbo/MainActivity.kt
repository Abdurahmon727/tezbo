package com.xda727.tezbo


import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.xda727.tezbo.service.ActionType
import com.xda727.tezbo.service.ForegroundService
import com.xda727.tezbo.ui.theme.TezboTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        ActivityCompat.requestPermissions(
//            this,
//            arrayOf(
//                Manifest.permission.DYNAMIC_RECEIVER_NOT_EXPORTED_PERMISSION,
//
//                ), 0
//        )
        setContent {
            TezboTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Button(onClick = {
                            Intent(applicationContext, ForegroundService::class.java).also {
                                it.action = ActionType.START.toString()
                                startService(it)
                            }
                        }) {
                            Text("Start")
                        }

                        Button(
                            onClick = {
                                Intent(applicationContext, ForegroundService::class.java).also {
                                    it.action = ActionType.STOP.toString()
                                    startService(it)
                                }
                            }
                        ) {
                            Text("End")
                        }

                    }
                }
            }
        }
    }
}
