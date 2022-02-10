package com.example.room.fragments.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.room.R
import com.example.room.core.BaseFragment
import com.example.room.ViewModel.ViewModel
import com.example.room.model.User
import com.example.room.databinding.FragmentAddUserBinding


class addUser : BaseFragment<FragmentAddUserBinding>() {
  lateinit var mViewModel: ViewModel
    private var viewBinding : FragmentAddUserBinding? = null
    protected val binding get() = checkNotNull(viewBinding)

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentAddUserBinding.inflate(inflater,container,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewBinding?.button?.setOnClickListener{
            insertDatainDatabase()
        }
        super.onViewCreated(view, savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        viewBinding = initBinding(inflater,container)


        mViewModel = ViewModelProvider(this).get(ViewModel::class.java)
        return  viewBinding!!.root
    }



    private fun insertDatainDatabase() {
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

