package com.murat.taskmanager.ui.task
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.murat.taskmanager.App
import com.murat.taskmanager.R
import com.murat.taskmanager.data.model.Task
import com.murat.taskmanager.databinding.FragmentTaskBinding

class TaskFragment : Fragment() {
    private lateinit var binding: FragmentTaskBinding
    private var task : Task? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       binding = FragmentTaskBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val value = it.getSerializable("task")
            if (value != null){
                task = value as Task
            }
            binding.etTitle.setText(task?.title.toString())
            binding.etDesc.setText(task?.desc.toString())
            if (task != null)
                binding.btnSave.text = "Update"
            else binding.btnSave.text = "Save" }




        binding.btnSave.setOnClickListener {
            if (binding.etTitle.text.toString().isNotEmpty()){
                if (task != null){
                    upDateTask()
                }
               else saveTask()
            }else{binding.etTitle.error = getString(R.string.error_title)}
        }


    }

    private fun upDateTask() {

        task?.title = binding.etTitle.text.toString()
        task?.desc = binding.etDesc.text.toString()

        task?.let { App.db.taskDao().update(it) }
        findNavController().navigateUp()    }

    private fun saveTask(){
        val data = Task(
      title =  binding.etTitle.text.toString(),
       desc = binding.etDesc.text.toString()
    )

        App.db.taskDao().insert(data)
        findNavController().navigateUp()
    }



}