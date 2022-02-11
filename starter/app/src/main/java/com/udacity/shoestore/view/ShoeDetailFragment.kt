package com.udacity.shoestore.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.viewmodel.ShoesViewModel

class ShoeDetailFragment : Fragment() {

    private lateinit var binding: FragmentShoeDetailBinding
    private val viewModel: ShoesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentShoeDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            binding.lifecycleOwner = lifecycleOwner
            binding.shoe = viewModel.shoe.value

            saveBtn.setOnClickListener {
                if (isFormValid()) {
                    viewModel.addShoe(binding.shoe)
                } else {
                    displayErrorMessage()
                }
            }

            cancelBtn.setOnClickListener {
                findNavController().popBackStack()
            }

            viewModel.shoeAddedEvent.observe(viewLifecycleOwner) { isAdded ->
                if (isAdded) {
                    findNavController().popBackStack()
                }
            }
        }
    }

    private fun displayErrorMessage() {
        Snackbar.make(binding.root,
            getString(R.string.error_message_complete_fields),
            Snackbar.LENGTH_SHORT).show()
    }

    private fun isFormValid(): Boolean {
        with(binding) {
            return !nameEdit.text.isNullOrEmpty() ||
                    !companyEdit.text.isNullOrEmpty() ||
                    !descriptionEdit.text.isNullOrEmpty()
        }
    }
}