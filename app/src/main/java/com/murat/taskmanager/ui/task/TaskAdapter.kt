package com.murat.taskmanager.ui.task

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.murat.taskmanager.data.model.Task
import com.murat.taskmanager.databinding.ItemTaskBinding

class TaskAdapter (var onClick:(task: Task)->Unit): RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {
    private val tasks = arrayListOf<Task>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(ItemTaskBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(tasks[position])
    }

   /* fun addTask(task: Task){
        tasks.add(0,task)
        notifyItemChanged(0)
    }*/
    @SuppressLint("NotifyDataSetChanged")
    fun addTasks(tasks : List<Task>){
        this.tasks.clear()
        this.tasks.addAll(tasks)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun deleteItem(task: Task){
        tasks.remove(task)
        notifyDataSetChanged()

    }

    override fun getItemCount(): Int = tasks.size


    inner class TaskViewHolder(private var binding: ItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(task: Task) {
            itemView.setOnLongClickListener() {
                onClick(task)
                false
            }
            binding.tvTitle.text = task.title
            binding.tvDescription.text = task.desc


        }
    }
}