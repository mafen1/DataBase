package com.example.room.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.room.R
import com.example.room.RecyclerView.Adapter
import com.example.room.core.BaseFragment
import com.example.room.ViewModel.ViewModel
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
        init()
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
    fun init(){
        setHasOptionsMenu(true)
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            deleteAllUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            viewModel.deleteAllUsers()
            Toast.makeText(requireContext(),
                "User Delete",
                Toast.LENGTH_LONG)
                .show()

        }
        builder.setNegativeButton("No") { _, _ ->}
        builder.setTitle("You want delete everything")
        builder.setTitle("Are you sure you want to delete everything?")
        builder.create().show()

    }
}