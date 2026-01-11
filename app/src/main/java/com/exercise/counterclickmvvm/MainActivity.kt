package com.exercise.counterclickmvvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.exercise.counterclickmvvm.ui.theme.CounterClickMVVMTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CounterClickMVVMTheme {
                MainScreen()
            }
        }
    }

    @Composable
    fun MainScreen() {
        // 'remember' hace que el valor se mantenga durante la vida del Composable
        // 'mutableStateOf' le dice a Compose: "Si esto cambia, redibuja lo que lo use"
        var counter by remember { mutableStateOf(0) }

        Column (modifier = Modifier.padding(8.dp)
            .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "$counter",
                fontSize = 44.sp,
                fontWeight = FontWeight.Bold
            )
            Button(
                onClick = {
                    counter++
                },
                modifier = Modifier.padding(top = 8.dp),
                content = {
                    Text(text = "Aumentar")
                }
            )
        }
    }
}
