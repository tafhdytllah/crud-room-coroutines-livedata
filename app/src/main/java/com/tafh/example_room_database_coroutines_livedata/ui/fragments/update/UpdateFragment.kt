package com.tafh.example_room_database_coroutines_livedata.ui.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.tafh.example_room_database_coroutines_livedata.R
import com.tafh.example_room_database_coroutines_livedata.databinding.FragmentUpdateBinding
import com.tafh.example_room_database_coroutines_livedata.model.User
import com.tafh.example_room_database_coroutines_livedata.viewmodel.UserViewModel

class UpdateFragment : Fragment() {

    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: UserViewModel

    private val args by navArgs<UpdateFragmentArgs>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentUpdateBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.apply {
            updateFirstName.setText(args.currentUser.firstName)
            updateLastName.setText(args.currentUser.lastName)
            updateAge.setText(args.currentUser.age.toString())

            btnUpdateUser.setOnClickListener{
                updateItem()
            }
        }

        setHasOptionsMenu(true)

        return binding.root
    }

    private fun updateItem() {
        val firstName = binding.updateFirstName.text.toString()
        val lastName = binding.updateLastName.text.toString()
        val age = binding.updateAge.text.toString()

        if (inputCheck(firstName, lastName, age)) {
            val updatedUser = User(args.currentUser.id, firstName, lastName, age.toInt())

            viewModel.updateUser(updatedUser)
            Toast.makeText(context, "Updated successfully!", Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {

            Toast.makeText(context, "Please fill out all fields!", Toast.LENGTH_SHORT).show()
        }

    }

    private fun inputCheck(firstName: String, lastName: String, age: String): Boolean {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && TextUtils.isEmpty(age))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.item_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            deleteUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser() {
        val builder = AlertDialog.Builder(context)
        builder.apply {
            setPositiveButton("Yes") { _, _ ->
                viewModel.deleteUser(args.currentUser)
                Toast.makeText(context, "Successfully removed: ${args.currentUser.firstName}", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_updateFragment_to_listFragment)
            }
            setNegativeButton("No") { _, _ -> }
            setTitle("Delete ${args.currentUser.firstName}?")
            setMessage("Are you sure you want to delete ${args.currentUser.firstName}")
            create().show()
        }

    }

}