package com.example.room.RecyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.room.model.User
import com.example.room.databinding.CustomBinding
import com.example.room.fragments.list.fragment_list
import com.example.room.fragments.list.fragment_listDirections
import com.example.room.fragments.update.UpdateFragmentDirections


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

        holder.binding.customView.setOnClickListener {
        val action = fragment_listDirections
            .actionFragmentListToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int = userList.size

    fun setData(user: List<User>){
        userList = user
        notifyDataSetChanged()

    }
}