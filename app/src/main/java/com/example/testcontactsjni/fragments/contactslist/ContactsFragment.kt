package com.example.testcontactsjni.fragments.contactslist

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.testcontactsjni.R
import com.example.testcontactsjni.databinding.FragmentContactsBinding
import com.example.testcontactsjni.viewmodel.MainActivityViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ContactsFragment : Fragment() {

    private var columnCount = 1
    private lateinit var contactsAdapter: ContactsAdapter
    private val model by sharedViewModel<MainActivityViewModel>()
    private lateinit var binding: FragmentContactsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        columnCount = model.columnCount

        binding = FragmentContactsBinding.inflate(inflater, container, false)
        binding.model = model

        contactsAdapter = ContactsAdapter { contact ->
            model.apply {
                currentContact.set(contact)
                isErrorDialog.set(false)
                dialogTitle.set(getString(R.string.delete_title))
            }
            findNavController().navigate(R.id.confirmFragment)
        }

        model.subscribeToContacts().observe(viewLifecycleOwner) {
                list ->
            contactsAdapter.updateValues(list)
            binding.list.visibility = if (list.isEmpty()) View.GONE else View.VISIBLE
            binding.listPlaceholder.visibility = if (list.isEmpty()) View.VISIBLE else View.GONE
        }

        val view = binding.listLay.apply {
            binding.apply {
                list.apply {
                    layoutManager = when {
                        columnCount <= 1 -> LinearLayoutManager(context)
                        else -> GridLayoutManager(context, columnCount)
                    }
                    adapter = contactsAdapter
                    visibility = if (contactsAdapter.itemCount == 0) View.GONE else View.VISIBLE
                }
                fabAdd.setOnClickListener {
                    findNavController().navigate(R.id.newContactFragment)
                }
            }

        }

        return view
    }
}