package com.murat.taskmanager.ui.onBoarding

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.murat.taskmanager.R
import com.murat.taskmanager.data.OnBoard
import com.murat.taskmanager.databinding.FragmentOnBoardingBinding
import com.murat.taskmanager.ui.onBoarding.adapter.OnBoardingAdapter


class OnBoardingFragment : Fragment() {
    lateinit var binding: FragmentOnBoardingBinding
    var onBoard = arrayListOf<OnBoard>()
    private lateinit var adapter : OnBoardingAdapter


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
        adapter = OnBoardingAdapter()
        binding.viewPager.adapter = adapter
        binding.indicator.setViewPager(binding.viewPager)
        adapter.registerAdapterDataObserver(binding.indicator.adapterDataObserver)
        binding.viewPager.registerOnPageChangeCallback(onPageChangeListener)

        binding.btnStarted.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.skip.setOnClickListener {
            findNavController().navigateUp()
        }
    }


    private val onPageChangeListener = object : OnPageChangeCallback(){
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)

            binding.btnStarted.isVisible = position == adapter.itemCount - 1

            binding.skip.isVisible = position != adapter.itemCount - 1
        }
    }

}