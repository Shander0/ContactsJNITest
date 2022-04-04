package com.example.testcontactsjni.fragments.dialogs

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.Observable
import androidx.fragment.app.DialogFragment
import com.example.testcontactsjni.R
import com.example.testcontactsjni.databinding.FragmentConfirmBinding
import com.example.testcontactsjni.databinding.FragmentNewContactBinding
import com.example.testcontactsjni.viewmodel.MainActivityViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ConfirmFragment : DialogFragment() {

    private val model by sharedViewModel<MainActivityViewModel>()
    private lateinit var binding: FragmentConfirmBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        model.dialogDismiss.observe(viewLifecycleOwner) {
            if (it) {
                model.dialogDismiss.value = false
                dismiss()
            }
        }
        binding = FragmentConfirmBinding.inflate(inflater, container, false)
            .apply { viewModel = model }
        return binding.dlgLay
    }
}