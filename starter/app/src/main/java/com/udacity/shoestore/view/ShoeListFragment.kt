package com.udacity.shoestore.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.ShoeItemBinding
import com.udacity.shoestore.viewmodel.ShoesViewModel

class ShoeListFragment : Fragment() {

    lateinit var binding: FragmentShoeListBinding
    private val viewModel: ShoesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentShoeListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            addFab.setOnClickListener {
                goToDetailsScreen()
            }

            viewModel.shoeList.observe(viewLifecycleOwner) { shoeList ->
                shoeList.forEach { shoe ->
                    val shoeItemView =
                        ShoeItemBinding.inflate(layoutInflater, binding.container, false)

                    shoeItemView.root.id = View.generateViewId()
                    shoeItemView.nameTv.text = shoe.name
                    shoeItemView.companyTv.text = shoe.company
                    shoeItemView.sizeTv.text = shoe.size.toString()
                    shoeItemView.descriptionTv.text = shoe.description

                    binding.container.addView(shoeItemView.root)
                }
            }
        }
    }

    private fun goToDetailsScreen() {
        findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment())
    }
}