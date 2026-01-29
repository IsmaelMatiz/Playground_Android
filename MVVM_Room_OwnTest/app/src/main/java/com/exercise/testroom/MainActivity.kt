package com.exercise.testroom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.exercise.testroom.ModelViews.Factories.MainVMFactory
import com.exercise.testroom.ModelViews.MainViewModel
import com.exercise.testroom.data.source.db.AppDB
import com.exercise.testroom.data.source.repos.UsersRepository
import com.exercise.testroom.ui.theme.TestRoomTheme
import com.exercise.testroom.ui.views.MainScreen
import kotlin.getValue

class MainActivity : ComponentActivity() {

    val viewModel: MainViewModel by viewModels {
        val userDao = AppDB.getInstance(this).userDao()
        val businessDao = AppDB.getInstance(this).businessDao()
        val repository = UsersRepository(userDao, businessDao)

        MainVMFactory(repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MainScreen(viewModel, context = baseContext)
        }
    }
}