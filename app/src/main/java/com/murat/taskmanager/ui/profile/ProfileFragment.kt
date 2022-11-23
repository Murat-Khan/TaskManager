package com.murat.taskmanager.ui.profile
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.murat.taskmanager.data.local.Pref
import com.murat.taskmanager.databinding.FragmentProfileBinding
import com.murat.taskmanager.loadImage



class ProfileFragment : Fragment() {
    lateinit var pref: Pref



    private lateinit var binding: FragmentProfileBinding




    private var galleryActivityLauncher: ActivityResultLauncher<Array<String>> =
        registerForActivityResult(
            ActivityResultContracts.OpenDocument()
        ) { result ->
            if (result != null) {
                binding.profileImage.loadImage(result.toString())
                pref.saveImage(result.toString())
            } else {
                Log.d("lol", "onActivityResult: the result is null for some reason")
            }
        }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.profileImage.setOnClickListener {
            galleryActivityLauncher.launch(arrayOf("image/*"))

        }
        saveProfileData()


    }



    @SuppressLint("SuspiciousIndentation")
    private fun saveProfileData() {
        pref = Pref(requireContext())
        binding.etName.setText(pref.getName())
        binding.etName.addTextChangedListener {
            pref.saveName(binding.etName.text.toString())
        }
        binding.etAge.setText(pref.getAge())
        binding.etAge.addTextChangedListener {
            pref.saveAge(binding.etAge.text.toString())
        }

        if (!pref.getImage().isNullOrEmpty())
        binding.profileImage.loadImage(pref.getImage())




    }


  }
















