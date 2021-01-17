package com.tafh.example_room_database_coroutines_livedata.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.tafh.example_room_database_coroutines_livedata.databinding.ItemUserBinding
import com.tafh.example_room_database_coroutines_livedata.model.User
import com.tafh.example_room_database_coroutines_livedata.ui.fragments.list.ListFragmentDirections


class ListAdapter : RecyclerView.Adapter<ListAdapter.ListViewHolder>(){

    private var userList = emptyList<User>()

    inner class ListViewHolder(private val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(userList: User) {
            binding.apply {
                tvIdUser.text = userList.id.toString()
                tvFirstName.text = userList.firstName.toString()
                tvLastName.text = userList.lastName.toString()
                tvAge.text = "(${userList.age})"

                binding.root.setOnClickListener { view ->

                    val action = ListFragmentDirections.actionListFragmentToUpdateFragment(userList)
                    view.findNavController().navigate(action)

                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(userList[position])
    }

    override fun getItemCount(): Int = userList.size

    fun setData(user: List<User>) {
        this.userList = user
        notifyDataSetChanged()
    }
}