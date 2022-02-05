package com.example.room.fragments.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.room.R
import com.example.room.databinding.FragmentListBinding


class fragment_list : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentListBinding.inflate(inflater)

        binding.floatingActionButton.setOnClickListener{
            findNavController().navigate(R.id.action_fragment_list_to_addUser)
        }

       return binding.root
    }


}