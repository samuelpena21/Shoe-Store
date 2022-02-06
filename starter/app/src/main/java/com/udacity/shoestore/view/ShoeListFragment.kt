package com.udacity.shoestore.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.ShoeItemBinding
import com.udacity.shoestore.viewmodel.ShoesViewModel
import com.udacity.shoestore.viewmodel.UserViewModel

class ShoeListFragment : Fragment() {

    lateinit var binding: FragmentShoeListBinding
    private val viewModel: ShoesViewModel by activityViewModels()
    private val userViewModel: UserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentShoeListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
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

            userViewModel.logOutSuccessEvent.observe(viewLifecycleOwner) { logOutEvent ->
//                if (logOutEvent) {
//                    findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToWelcomeFragment())
//                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.drawer_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.logout -> logOut()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun goToDetailsScreen() {
        findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment())
    }

    private fun logOut() {
        findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToWelcomeFragment())
        userViewModel.logOut()
    }
}