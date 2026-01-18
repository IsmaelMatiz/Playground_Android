package com.exercise.mytaskmanager.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.exercise.mytaskmanager.R
import com.exercise.mytaskmanager.data.Task
import com.exercise.mytaskmanager.databinding.ItemTaskBinding

class TaskAdapter(
    private var tasks: List<Task> = emptyList(),
    private val onItemClick: (Task) -> Unit,
    private val onItemLongClick: (Task) -> Unit
): RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = ItemTaskBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return  TaskViewHolder(binding)
    }

    override fun getItemCount(): Int = tasks.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]
        holder.bind(task, onItemClick, onItemLongClick)
    }

    fun updateTasks(newTasks: List<Task>) {
        tasks = newTasks
        notifyDataSetChanged()
    }

    class TaskViewHolder(
        private val binding: ItemTaskBinding
    ) : RecyclerView.ViewHolder(binding.root)
    {
        fun bind(task: Task, onItemClick: (Task) -> Unit, onItemLongClick: (Task) -> Unit) {
            binding.tvTaskTitle.text = task.title
            binding.tvTaskDescription.text = task.description
            binding.tvPriority.text = task.getPriorityText()
            binding.tvStatus.text = if (task.isCompleted) "Completed" else "Pending"

            binding.viewPriorityIndicator.setBackgroundColor(task.getPriorityColor().toInt())

            val context = binding.root.context

            if (task.isCompleted) {
                binding.tvTaskTitle.setTextColor(ContextCompat.getColor(context, R.color.gray))
                binding.tvStatus.setTextColor(ContextCompat.getColor(context, R.color.green))
            } else {
                binding.tvTaskTitle.setTextColor(ContextCompat.getColor(context, R.color.black))
                binding.tvStatus.setTextColor(context.getColor(R.color.white))
            }

            binding.root.setOnClickListener { onItemClick(task) }
            binding.root.setOnLongClickListener {
                onItemLongClick(task)
                true
            }

        }
    }



}
