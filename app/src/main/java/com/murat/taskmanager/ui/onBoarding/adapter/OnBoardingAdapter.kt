package com.murat.taskmanager.ui.onBoarding.adapter



import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter

import com.murat.taskmanager.data.OnBoard
import com.murat.taskmanager.databinding.ItemOnBoardingBinding

import com.murat.taskmanager.loadImage



class OnBoardingAdapter : Adapter<OnBoardingAdapter.OnBoardingViewHolder>() {

    private val arrayList = arrayListOf<OnBoard>(
        OnBoard("https://img.freepik.com/free-vector/business-man-with-a-pencil-a-marked-checklist-on-a-clipboard-paper-successful-forming-of-business-tasks-flat-illustration_114482-134.jpg?size=626&ext=jpg","Manage your Task", "Organize all your to-dos in lists and projects. Color tog them to set prionties ond cotegories."),
        OnBoard("https://kolejidea.com/wp-content/uploads/2020/07/idea-koleji-burs.jpg","Work on Time","When you're overwheimed by the amount af work you have on your plate, stop and rethink."),
        OnBoard("https://thumbs.dreamstime.com/b/символ-офисный-работник-выполняемые-с-заднее-поджечь-крайний-срок-и-в-179595708.jpg","Get reminder on time","When you encounter a small task that tokes less than 5 minutes to complete, just get it done.")
    )
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
       return OnBoardingViewHolder(
           com.murat.taskmanager.databinding.ItemOnBoardingBinding.inflate(
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
            binding.onBoardDesc.text =onBoard.desc
            binding.onBordImage.loadImage(onBoard.image)
        }
        }


}