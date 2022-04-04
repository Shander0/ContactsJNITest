package com.example.testcontactsjni.fragments.contactslist

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.example.domain.entities.Contact
import com.example.testcontactsjni.databinding.ContactsItemBinding

class ContactsAdapter(private val onSelect: (Contact?) -> Unit) : RecyclerView.Adapter<ContactsAdapter.ViewHolder>() {

    private var values: List<Contact> = emptyList()

    fun updateValues(list: List<Contact>?) {
        values = list ?: emptyList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            ContactsItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(values[position], onSelect)
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(private val binding: ContactsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val name: TextView = binding.contactName
        private val phone: TextView = binding.contactPhone

        fun bind(contact: Contact, onSelect: (Contact?) -> Unit) {
            name.text = contact.name
            phone.text = contact.phone
            binding.root.setOnClickListener {
                onSelect(contact)
            }
        }
    }

}