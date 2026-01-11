package com.exercise.counterclickmvvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
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
import com.exercise.counterclickmvvm.viewmodel.CounterViewModel

class MainActivity : ComponentActivity() {

    // ✅ ESTA ES LA FORMA CORRECTA    // 'viewModels()' es un "delegate" que se encarga de:
    // - Si es la primera vez, crea el ViewModel.
    // - Si es una recreación (rotación), te da el que ya existía.
    private val viewModel by viewModels<CounterViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CounterClickMVVMTheme {
                MainScreen(viewModel)
            }
        }
    }

    @Composable
    fun MainScreen(vm: CounterViewModel) {
        val counter by vm.counter


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
                    vm.increaseCounter()
                },
                modifier = Modifier.padding(top = 8.dp),
                content = {
                    Text(text = "Aumentar")
                }
            )
        }
    }
}
