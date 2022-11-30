package com.murat.taskmanager.ui.task

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.murat.taskmanager.R
import com.murat.taskmanager.data.model.Task
import com.murat.taskmanager.databinding.ItemTaskBinding

class TaskAdapter (
    private val onLongClick:(task: Task)->Unit,
    private val onClick :(Task)->Unit,
private val context: Context

): RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {
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



        fun bind(task: Task ) {
            itemView.setOnLongClickListener {
                onLongClick(task)
                false
            }
            binding.tvTitle.text = task.title
            binding.tvDescription.text = task.desc

            binding.root.setOnClickListener {
                onClick(task)
            }
            if (adapterPosition%2 == 0){
                binding.root.setBackgroundColor(ContextCompat.getColor(context,R.color.black))
                binding.tvDescription.setTextColor(ContextCompat.getColor(context,R.color.white))
                binding.tvTitle.setTextColor(ContextCompat.getColor(context,R.color.white))

            }else{
                binding.root.setBackgroundColor(ContextCompat.getColor(context, R.color.white))
                binding.tvDescription.setTextColor(ContextCompat.getColor(context, R.color.black))
                binding.tvTitle.setTextColor(ContextCompat.getColor(context, R.color.black))
            }


        }
    }
}