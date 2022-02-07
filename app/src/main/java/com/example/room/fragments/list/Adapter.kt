package com.example.room.fragments.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.room.data.User
import com.example.room.databinding.CustomBinding

class Adapter: RecyclerView.Adapter<Adapter.ViewHolder>() {
    private var userList = emptyList<User>()
    class ViewHolder(val binding: CustomBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return ViewHolder(CustomBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = userList[position]
        val id = currentItem.id.toString()
        holder.binding.firstName.text = currentItem.name
        holder.binding.lastName.text = currentItem.female
        holder.binding.age.text = currentItem.age.toString()
        holder.binding.number.text = id
    }

    override fun getItemCount(): Int = userList.size

    fun setData(user: List<User>){
        userList = user
        notifyDataSetChanged()

    }
}