package com.murat.taskmanager.ui.home

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.murat.taskmanager.R
import com.murat.taskmanager.data.Task
import com.murat.taskmanager.databinding.FragmentHomeBinding
import com.murat.taskmanager.ui.task.TaskAdapter

class HomeFragment : Fragment() {

private var _binding: FragmentHomeBinding? = null
  private lateinit var adapter : TaskAdapter
  private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = TaskAdapter()

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setFragmentResultListener(
            "fr_task"
        ) { _, result ->
            val task = result.getSerializable("task") as Task
            adapter.addTask(task)


        }

        binding.taskRecycler.adapter = adapter
        binding.fabEdit.setOnClickListener {
            findNavController().navigate(R.id.taskFragment)
        }
    }

override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}