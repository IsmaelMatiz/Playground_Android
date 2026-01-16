package com.exercise.mytaskmanager

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    /**
     * Important Concept
     * The main idea of this projects is to get a better understanding of the Android Architecture.
     * good practices, etc. Something important that I dint get completely was why it is important
     * the repository and factory labels but with this project I just get it and this could be
     * the summary
     * DTO/Entity → define or map the data.
     * DAO → access to the DB or better be handling the operations to the DB.
     * Repository → Manage and abstract the data sources.
     * ViewModel → Manage UI status and logic.
     * Factory → Allows dependency injection at ViewModel.
     *
     * With this structure I'll get a clean code, separation of responsibilities, scalability,
     * Reuse and maintainability. Also compatibility with the Lifecycle, cause with a good
     * implementation of MVVM like this one I wont lose any data when the device rotates or
     * the app is in the background.
     *
     * Whit all of this now I have a better understanding of the Android Architecture and how to
     * use it correctly.
     *
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}