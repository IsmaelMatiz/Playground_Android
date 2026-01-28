package com.exercise.mytaskmanager.ui

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.exercise.mytaskmanager.R
import com.exercise.mytaskmanager.data.Task
import com.exercise.mytaskmanager.data.TaskDatabase
import com.exercise.mytaskmanager.databinding.ActivityMainBinding
import com.exercise.mytaskmanager.databinding.DialogTaskBinding
import com.exercise.mytaskmanager.repository.TaskRepository
import com.exercise.mytaskmanager.ui.adapter.TaskAdapter
import com.exercise.mytaskmanager.viewmodel.TaskViewModel
import com.exercise.mytaskmanager.viewmodel.TaskViewModelFactory

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

    private lateinit var adapter: TaskAdapter
    private lateinit var binding: ActivityMainBinding
    private val viewModel: TaskViewModel by viewModels {
        val database = TaskDatabase.getDatabase(this)
        val repository = TaskRepository(database.taskDao())
        TaskViewModelFactory(repository)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        setupClickListeners()
        observeTasks()
    }

    private fun setupRecyclerView(){
        adapter = TaskAdapter(
            onItemClick = { task ->
                showTaskDialog(task)
            },
            onItemLongClick = { task ->
                    showDeleteConfirmation(task)
            }
        )
        binding.rvTasks.layoutManager = LinearLayoutManager(this)
        binding.rvTasks.adapter = adapter
    }

    private fun setupClickListeners(){
        binding.btnAddTask.setOnClickListener{
            showTaskDialog()
        }
    }

    private fun observeTasks(){
        viewModel.allTasks.observe(this){tasks ->
            adapter.updateTasks(tasks)
            if(tasks.isEmpty()){
                binding.rvTasks.visibility = View.GONE
                binding.emptyState.visibility = View.VISIBLE
            }else{
                binding.rvTasks.visibility = View.VISIBLE
                binding.emptyState.visibility = View.GONE
            }
        }
    }

    private fun showDeleteConfirmation(task: Task) {
        AlertDialog.Builder(this)
            .setTitle("Delete Task")
            .setMessage("Are you sure you want to delete '${task.title}'?")
            .setPositiveButton("Delete"){_,_->
                viewModel.deleteTask(task)
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun showTaskDialog(existingTask: Task? = null) {

        val dialogBinding = DialogTaskBinding.inflate(layoutInflater)
        val dialog = AlertDialog.Builder(this)
            .setView(dialogBinding.root)
            .create()

        existingTask?.let { task ->
            dialogBinding.etTitle.setText(task.title)
            dialogBinding.etDescription.setText(task.description)
            dialogBinding.cbCompleted.isChecked = task.isCompleted

            when(task.priority){
                1L -> dialogBinding.rbLow.isChecked = true
                2L -> dialogBinding.rbMedium.isChecked = true
                3L -> dialogBinding.rbHigh.isChecked = true
            }

            dialogBinding.btnSave.text = "Update"


        }

        dialogBinding.btnCancel.setOnClickListener{
            dialog.dismiss()
        }

        dialogBinding.btnSave.setOnClickListener{
            val title = dialogBinding.etTitle.text.toString().trim()
            val description = dialogBinding.etDescription.text.toString().trim()
            if (title.isEmpty()){
                dialogBinding.etTitle.error = "title is required"
                return@setOnClickListener
            }
            val priority = when(dialogBinding.rgPriority.checkedRadioButtonId){
                R.id.rbLow-> 1
                R.id.rbMedium-> 2
                R.id.rbHigh-> 3
                else -> 1
            }
            val isCompleted = dialogBinding.cbCompleted.isChecked

            val task = existingTask?.copy(
                title = title,
                description = description,
                priority = priority.toLong(),
                isCompleted = isCompleted
            ) ?: Task(

                title = title,
                description = description,
                priority = priority.toLong(),
                isCompleted = isCompleted
            )

            if (existingTask !=null){
                viewModel.updateTask(task)
            }else{
                viewModel.insertTask(task)
            }

            dialog.dismiss()

        }
        dialog.show()

    }
    
}