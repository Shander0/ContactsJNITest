package com.example.testcontactsjni.fragments.newcontact

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import com.example.testcontactsjni.R
import com.example.testcontactsjni.databinding.FragmentNewContactBinding
import com.example.testcontactsjni.viewmodel.MainActivityViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class NewContactFragment : Fragment() {

    private val model by sharedViewModel<MainActivityViewModel>()
    private lateinit var binding: FragmentNewContactBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(this) {
            findNavController().navigateUp()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        model.errorExists.observe(viewLifecycleOwner) {
            if (model.errorExists.value!!) {
                findNavController().navigate(R.id.confirmFragment)
            }
        }

        model.insertSuccess.observe(viewLifecycleOwner) {
            if (model.insertSuccess.value!!) {
                model.insertSuccess.value = false
                model.enteringName.set("")
                model.enteringPhone.set("")
                findNavController().navigateUp()
            }
        }

        binding = FragmentNewContactBinding.inflate(inflater, container, false)
            .apply { viewModel = model }
        return binding.newLay
    }

}