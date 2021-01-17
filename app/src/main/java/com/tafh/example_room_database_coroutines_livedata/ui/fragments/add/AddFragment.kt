package com.tafh.example_room_database_coroutines_livedata.ui.fragments.add

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.tafh.example_room_database_coroutines_livedata.R
import com.tafh.example_room_database_coroutines_livedata.model.User
import com.tafh.example_room_database_coroutines_livedata.databinding.FragmentAddBinding
import com.tafh.example_room_database_coroutines_livedata.viewmodel.UserViewModel


class AddFragment : Fragment(R.layout.fragment_add) {

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        val view = binding.root

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.btnAddUser.setOnClickListener {
            insertDataToDatabase()
        }


        return view
    }

    private fun insertDataToDatabase() {

        binding.apply {
            val firstName = etFirstName.text.toString()
            val lastName = etLastName.text.toString()
            val age = etAge.text.toString()

            if (inputCheck(firstName, lastName, age)) {
                val user = User(0, firstName, lastName, age.toInt())

                viewModel.addUser(user)
                Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_SHORT).show()

                findNavController().navigate(R.id.action_addFragment_to_listFragment)
            } else {

                Toast.makeText(requireContext(), "Please fill out all fields!", Toast.LENGTH_SHORT).show()
            }


        }


    }

    private fun inputCheck(firstName: String, lastName: String, age: String): Boolean {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && TextUtils.isEmpty(age))
    }


}