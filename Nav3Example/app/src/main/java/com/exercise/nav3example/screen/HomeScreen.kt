package com.exercise.nav3example.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(navigateToDetail: (String) -> Unit){
    Scaffold (content = {
        paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)){
            items(20){
                Box(contentAlignment = Alignment.CenterStart,
                    modifier = Modifier.padding(16.dp)
                        .height(50.dp).
                    fillMaxWidth()
                        .clickable{
                            navigateToDetail("$it")
                        }
                )
                {
                    Text("Posicion en la lista: $it")
                }

            }
        }
    })
}