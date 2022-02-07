package com.example.room.fragments.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.room.R
import com.example.room.core.BaseFragment
import com.example.room.data.ViewModel
import com.example.room.databinding.FragmentAddUserBinding
import com.example.room.databinding.FragmentListBinding


class fragment_list : BaseFragment<FragmentListBinding>() {
    lateinit var viewModel: ViewModel
    val adapter = Adapter()
    private var viewBinding: FragmentListBinding? = null
    protected val binding get() = checkNotNull(viewBinding)

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentListBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initAdapter()

        viewModel = ViewModelProvider(this).get(ViewModel::class.java)
        viewModel.readAllData.observe(viewLifecycleOwner, Observer { user ->
            adapter.setData(user)
        })
        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_fragment_list_to_addUser)
        }

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewBinding = initBinding(inflater,container)
        return viewBinding!!.root
    }



    fun initAdapter(){

        val recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

}