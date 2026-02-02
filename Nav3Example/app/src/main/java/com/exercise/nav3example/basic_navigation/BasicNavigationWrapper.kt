package com.exercise.nav3example.basic_navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import com.exercise.nav3example.screen.DetailScreen
import com.exercise.nav3example.screen.HomeScreen


data object  Home
data class  Detail(val id: String)

@Composable
fun BasicNavigationWrapper(){
    val backStack = remember { mutableStateListOf<Any>(Home) }

    NavDisplay(
        backStack= backStack,
        onBack = {backStack.removeLastOrNull()},
        entryProvider = { key ->
            when(key){
                is Home -> NavEntry(key=key){
                    HomeScreen{
                        backStack.add(Detail(it))
                    }
                }

                is Detail -> NavEntry(key=key){
                    DetailScreen(id = key.id, navigateBack = {backStack.removeLastOrNull()})
                }

                else -> NavEntry(key=Unit){
                    Text("Error :(")
                }
            }
        }
    )
}
