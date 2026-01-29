package com.exercise.testroom.ui.views

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.forEach
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.exercise.testroom.ModelViews.MainViewModel

@Composable
fun MainScreen(
    mainViewModel: MainViewModel,
    context: Context
){
    val errorMessage by mainViewModel.errorMessage.collectAsState("")


    LaunchedEffect(errorMessage) {
        if (errorMessage.isNotEmpty()) {
            Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 35.dp, start = 15.dp, end = 15.dp)
    ) {
        // Todo lo que antes estaba en el Column ahora va dentro de un 'item'
        item {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp))
            {
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Nombre Completo") },
                    value = mainViewModel.fullName.value,
                    onValueChange = { mainViewModel.onFullNameChange(it) }
                )
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Edad") },
                    value = mainViewModel.age.value,
                    onValueChange = { mainViewModel.onAgeChange(it) }
                )
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Correo") },
                    value = mainViewModel.email.value,
                    onValueChange = { mainViewModel.onEmailChange(it) }
                )
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Nombre de Negocio") },
                    value = mainViewModel.businessName.value,
                    onValueChange = { mainViewModel.onBusinessNameChange(it) }
                )
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Link de Imagen") },
                    value = mainViewModel.businessImage.value,
                    onValueChange = { mainViewModel.onBusinessImageChange(it) }
                )

                Button(
                    modifier = Modifier.padding(vertical = 16.dp),
                    onClick = { mainViewModel.createUser() }
                ) {
                    Text("Crear Usuario con o sin Negocio")
                }
            }

        }

        if (mainViewModel.usersWithBusinesses.value.isNotEmpty()) {
            // Los elementos de la lista se añaden directamente al LazyColumn principal
            items(mainViewModel.usersWithBusinesses.value.size) { index ->
                val userWithBusinesses = mainViewModel.usersWithBusinesses.value[index]
                val user = userWithBusinesses.user
                val businesses = userWithBusinesses.businesses

                Column(modifier = Modifier.padding(vertical = 8.dp).
                    pointerInput(Unit){
                        detectTapGestures (
                            onLongPress = {
                                mainViewModel.deleteUser(user.id)
                            }
                        )
                    }
                ) {
                    Text(
                        text = "Nombre: ${user.fullName}, Edad: ${user.age}, Email: ${user.email}",
                        style = androidx.compose.material3.MaterialTheme.typography.bodyLarge
                    )
                    businesses.forEach { business ->
                        Text(
                            modifier = Modifier.padding(start = 15.dp),
                            text = "Negocio: ${business.name}, Imagen: ${business.image}",
                            style = androidx.compose.material3.MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
        }else{
            item {
                Text( text = "No hay usuarios registrados",
                     modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}
