package com.murat.taskmanager.ui.onBoarding.adapter



import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.murat.taskmanager.R

import com.murat.taskmanager.data.model.OnBoard
import com.murat.taskmanager.databinding.ItemOnBoardingBinding





class OnBoardingAdapter : Adapter<OnBoardingAdapter.OnBoardingViewHolder>() {

    private val arrayList = arrayListOf<OnBoard>(
        OnBoard(R.raw.lottie_1,"Manage your Task", "Organize all your to-dos in lists and projects. Color tog them to set priorities ond categories."),
        OnBoard(R.raw.lottie_2,"Work on Time" , "When you're overwhelmed by the amount af work you have on your plate, stop and rethink."),
        OnBoard(R.raw.lottie_3,"Get reminder on time","When you encounter a small task that tokes less than 5 minutes to complete, just get it done.")
    )
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
       return OnBoardingViewHolder(
          ItemOnBoardingBinding.inflate(
           LayoutInflater.from(parent.context),parent,false))
    }


    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
       holder.onBind(arrayList[position])
    }

    override fun getItemCount(): Int = arrayList.size

    inner class OnBoardingViewHolder(private var binding: ItemOnBoardingBinding) :
        RecyclerView.ViewHolder(binding.root){
        fun onBind(onBoard: OnBoard){
            /* binding.btnStarted.isVisible = adapterPosition == arrayList.lastIndex
            binding.skip.isVisible = adapterPosition != arrayList.lastIndex*/
            binding.onBoardTitle.text = onBoard.title
            binding.onBoardDesc.text = onBoard.desc

            onBoard.image?.let { binding.onBordImage.setAnimation(it) }

        }
        }


}