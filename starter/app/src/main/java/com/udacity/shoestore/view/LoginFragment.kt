package com.udacity.shoestore.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentLoginBinding
import com.udacity.shoestore.viewmodel.UserViewModel

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    private val viewModel: UserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginBtn.setOnClickListener {
            if (validateInput()) {
                viewModel.login(
                    email = binding.emailEdit.text.toString(),
                    password = binding.passwordEdit.text.toString(),
                    requireContext())
            }
        }

        binding.createAccountTv.setOnClickListener {
            navigateToCreateAccountScreen()
        }

        viewModel.loginSuccessEvent.observe(viewLifecycleOwner) { successEvent ->
            if (successEvent == true) {
                if (viewModel.user.value?.hasTakenOnboarding == true) {
                    navigateToDetailsScreen()
                } else {
                    navigateToWelcomeScreen()
                }
            }
        }

        viewModel.errorLoginEvent.observe(viewLifecycleOwner) { errorEvent ->
            if (errorEvent == true) {
                displayErrorMessage()
            }
        }

        binding.emailEdit.doOnTextChanged { text, start, before, count ->
            binding.emailTl.isErrorEnabled = false
        }
    }

    private fun validateInput(): Boolean {
        //TODO: Add more types of validation.
        val email = binding.emailEdit.text.toString()
        val pass = binding.passwordEdit.text.toString()

        return email.isNotEmpty() && pass.isNotEmpty()
    }

    private fun displayErrorMessage() {
        binding.emailTl.error = getString(R.string.login_error_text)
        binding.emailTl.isErrorEnabled = true
    }

    private fun navigateToCreateAccountScreen() {
        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToCreateAccountFragment())
    }

    private fun navigateToWelcomeScreen() {
        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())
    }

    private fun navigateToDetailsScreen() {
        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToShoeListFragment())
    }
}