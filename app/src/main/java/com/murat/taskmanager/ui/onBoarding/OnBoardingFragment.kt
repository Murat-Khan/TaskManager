package com.murat.taskmanager.ui.onBoarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.google.firebase.auth.FirebaseAuth
import com.murat.taskmanager.R
import com.murat.taskmanager.data.local.Pref
import com.murat.taskmanager.data.model.OnBoard
import com.murat.taskmanager.databinding.FragmentOnBoardingBinding
import com.murat.taskmanager.ui.onBoarding.adapter.OnBoardingAdapter


class OnBoardingFragment : Fragment() {
    lateinit var binding: FragmentOnBoardingBinding
    lateinit var pref: Pref
    private lateinit var auth: FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnBoardingBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pref = Pref(requireContext())
        auth = FirebaseAuth.getInstance()
        val adapter = OnBoardingAdapter()
        binding.viewPager.adapter = adapter
        binding.indicator.setViewPager(binding.viewPager)
        adapter.registerAdapterDataObserver(binding.indicator.adapterDataObserver)
        binding.btnStarted.setOnClickListener {
            if (auth.currentUser?.uid == null){
                findNavController().navigate(R.id.auhtFragment)
            }else{
                pref.saveBoardingShow(true)
                findNavController().navigateUp()
            }

        }
        binding.skip.setOnClickListener {

            if (auth.currentUser?.uid == null){
                findNavController().navigate(R.id.auhtFragment)
            }else{
                pref.saveBoardingShow(true)
                findNavController().navigateUp()
            }
        }

        binding.viewPager.registerOnPageChangeCallback(object : OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.btnStarted.isVisible = position == adapter.itemCount - 1
                binding.skip.isVisible = position != adapter.itemCount - 1
            }
        })
    }
}