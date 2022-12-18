package com.murat.taskmanager.ui.home

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.murat.taskmanager.App
import com.murat.taskmanager.R
import com.murat.taskmanager.data.model.Task
import com.murat.taskmanager.databinding.FragmentHomeBinding
import com.murat.taskmanager.utils.showToast
import com.murat.taskmanager.ui.task.TaskAdapter

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var adapter: TaskAdapter
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = TaskAdapter(this::onLongClick, this::onClick, requireContext())

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


        val data = App.db.taskDao().getAllTask()



        adapter.addTasks(data)
        binding.taskRecycler.adapter = adapter
        binding.fabEdit.setOnClickListener {
            findNavController().navigate(R.id.taskFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun onClick(task: Task) {
        findNavController().navigate(R.id.taskFragment, bundleOf("task" to task))
    }


    private fun onLongClick(task: Task) {
        val dialogBinding = layoutInflater.inflate(R.layout.castom_dialog, null)
        val myDialog = Dialog(requireContext())
        myDialog.setContentView(dialogBinding)
        myDialog.setCancelable(true)
        myDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val cancel = dialogBinding.findViewById<TextView>(R.id.dialog_delete)
        val delete = dialogBinding.findViewById<TextView>(R.id.dialog_confirm)
        dialogBinding.findViewById<TextView>(R.id.dialog_title).text = "Удалить"
        dialogBinding.findViewById<TextView>(R.id.dialog_message).text =
            "Вы действительно хотите удалть заметку?"
        delete.text = "Удалить"
        cancel.text = "Отмена"
        delete.setOnClickListener {
            App.db.taskDao().delete(task)
            adapter.deleteItem(task)
            myDialog.dismiss()
            requireActivity().showToast("Заметка удалена")
        }


        cancel.setOnClickListener { myDialog.dismiss() }
        myDialog.show()
    }
}





