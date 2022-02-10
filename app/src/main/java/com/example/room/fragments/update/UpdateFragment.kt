package com.example.room.fragments.update

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.room.R
import com.example.room.ViewModel.ViewModel
import com.example.room.core.BaseFragment
import com.example.room.databinding.FragmentListBinding
import com.example.room.databinding.FragmentUpdateBinding
import com.example.room.model.User


class UpdateFragment : BaseFragment<FragmentUpdateBinding>() {

    private var viewBinding: FragmentUpdateBinding? = null
    protected val binding get() = checkNotNull(viewBinding)
    private val args by navArgs<UpdateFragmentArgs>()
    lateinit var viewModel: ViewModel

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentUpdateBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[ViewModel::class.java]
        binding.edUpdateName.setText(args.currentUser.name)
        binding.edUpdateFemale.setText(args.currentUser.female)
        binding.edUpdateAge.setText(args.currentUser.age.toString())

        binding.btnUpdateUser.setOnClickListener {
            updateData()
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewBinding = initBinding(inflater,container)

        return viewBinding!!.root
    }

    private fun updateData(){
        val firstName = binding.edUpdateName.text.toString()
        val lastName = binding.edUpdateFemale.text.toString()
        val Age = Integer.parseInt(binding.edUpdateAge.text.toString())
        if (inputCheck(firstName,lastName,binding.edUpdateAge.text)){
            val UpdateUser = User(args.currentUser.id, firstName, lastName, Integer.parseInt(Age.toString()))
            viewModel.updateUser(UpdateUser)
            Toast.makeText(requireContext(), "update was successful", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateFragment_to_fragment_list)
    }else{
            Toast.makeText(requireContext(), "update was not successful", Toast.LENGTH_LONG).show()
        }
    }
    fun inputCheck(firstName:String, lastName:String, age: Editable):Boolean{
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }

}