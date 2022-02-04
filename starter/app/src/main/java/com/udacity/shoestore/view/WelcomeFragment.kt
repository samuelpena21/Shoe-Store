package com.udacity.shoestore.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentWelcomeBinding
import com.udacity.shoestore.viewmodel.UserViewModel

class WelcomeFragment : Fragment() {

    lateinit var binding: FragmentWelcomeBinding

    private val viewModel: UserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            instructionsBtn.setOnClickListener { goToInstructionsScreen() }

            viewModel.user.observe(viewLifecycleOwner) { user ->
                if (user == null) {
                    navigateToLoginScreen()
                }
            }
        }
    }

    private fun goToInstructionsScreen() {
        findNavController().navigate(WelcomeFragmentDirections.actionWelcomeFragmentToInstructionFragment())
    }

    private fun navigateToLoginScreen() {
        findNavController().navigate(R.id.loginFragment)
    }
}