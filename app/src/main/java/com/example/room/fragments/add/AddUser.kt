package com.example.room.fragments.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.room.R
import com.example.room.data.ViewModel
import com.example.room.data.User
import com.example.room.databinding.FragmentAddUserBinding



class addUser : Fragment() {
  lateinit var mViewModel: ViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        val binding = FragmentAddUserBinding.inflate(inflater)

        binding.button.setOnClickListener{
            insertDatainDatabase(binding)
        }
        mViewModel = ViewModelProvider(this).get(ViewModel::class.java)
        return binding.root
    }



    private fun insertDatainDatabase(binding: FragmentAddUserBinding) {
        val firstName = binding.editTextTextPersonName.text.toString()
        val lastName = binding.editTextTextPersonName2.text.toString()
        val Age = binding.editTextTextPersonName3.text
        if (inputCheck(firstName,lastName,Age)){
            val user = User(0, firstName, lastName, Integer.parseInt(Age.toString()))
            mViewModel.addUser(user)
            Toast.makeText(requireContext(),
                "Successfully added",
                Toast.LENGTH_LONG)
                .show()
            findNavController().navigate(R.id.action_addUser_to_fragment_list)
        }else{
            Toast.makeText(requireContext(),
                "Please fill out all fields",
                Toast.LENGTH_LONG)
                .show()
        }
    }
    fun inputCheck(firstName:String, lastName:String, age:Editable):Boolean{
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }

}

