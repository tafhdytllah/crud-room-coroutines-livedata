package com.tafh.example_room_database_coroutines_livedata.ui.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tafh.example_room_database_coroutines_livedata.R
import com.tafh.example_room_database_coroutines_livedata.databinding.FragmentListBinding
import com.tafh.example_room_database_coroutines_livedata.ui.adapters.ListAdapter
import com.tafh.example_room_database_coroutines_livedata.viewmodel.UserViewModel

class ListFragment : Fragment(R.layout.fragment_list) {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        val view = binding.root

        val recyclerView = binding.rvListUser
        val mAdapter = ListAdapter()
        recyclerView.adapter = mAdapter
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        viewModel.getAllUser.observe(viewLifecycleOwner, Observer { listUser ->
            mAdapter.setData(listUser)
        })

        binding.fabAddUser.setOnClickListener{
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        setHasOptionsMenu(true)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.item_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            deleteAllUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllUser() {
        val builder = AlertDialog.Builder(context)
        builder.apply {
            setPositiveButton("Yes") { _, _ ->
                viewModel.deleteAllUser()
                Toast.makeText(context, "Successfully removed everything", Toast.LENGTH_SHORT).show()
            }
            setNegativeButton("No") { _, _ -> }
            setTitle("Delete everything?")
            setMessage("Are you sure you want to delete everything?")
            create().show()
        }
    }

}